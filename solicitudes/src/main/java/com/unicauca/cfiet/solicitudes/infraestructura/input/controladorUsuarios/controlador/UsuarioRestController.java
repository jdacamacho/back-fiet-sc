package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.UsuarioCUIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioLiviano;
import com.unicauca.cfiet.solicitudes.infraestructura.configuracion.lectorArchivos.ProcesadorArchivos;
import com.unicauca.cfiet.solicitudes.infraestructura.configuracion.lectorArchivos.validadoresArchivos.UsuarioExcelService;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioActualizarDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioLivianoDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.mapeador.MapperUsuarioInfraestructuraDominio;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@RestController
@RequestMapping("${url.application}usuarios")
@Validated
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de Usuarios.")
public class UsuarioRestController{
    private final UsuarioCUIntPuerto casoDeUso;
    private final MapperUsuarioInfraestructuraDominio mapper;
    private final ProcesadorArchivos procesadorArchivos;
    private final UsuarioExcelService usuarioExcelService;

    public UsuarioRestController(UsuarioCUIntPuerto casoDeUso,
                                 MapperUsuarioInfraestructuraDominio mapper,
                                 @Qualifier("archivos-usuarios") ProcesadorArchivos procesadorArchivos,
                                 UsuarioExcelService usuarioExcelService){
        this.casoDeUso = casoDeUso;
        this.mapper = mapper;
        this.procesadorArchivos = procesadorArchivos;
        this.usuarioExcelService = usuarioExcelService;
    }

    @GetMapping
    public ResponseEntity<?> index(@RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio){
        List<UsuarioLiviano> usuarios = casoDeUso.getUsuarios(pagina, tamanio);
        return new ResponseEntity<List<UsuarioLivianoDTORespuesta>>(
                mapper.mapearModelosARespuestaLiviano(usuarios), HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getUsuario(@PathVariable String uuid){
        Usuario usuario = casoDeUso.getUsuario(uuid);
        return new ResponseEntity<UsuarioDTORespuesta>(
                mapper.mapearModeloARespuesta(usuario), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTOPeticion peticion, @RequestParam String tipoUsuario){
        Usuario usuario;
        try{
            usuario = casoDeUso.crearUsuario(mapper.mapearPeticionAModelo(peticion), tipoUsuario);
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UsuarioDTORespuesta>(
                mapper.mapearModeloARespuesta(usuario), HttpStatus.OK
        );
    }

    @Transactional
    @PostMapping("/cargar/archivo")
    public ResponseEntity<?> crearUsuarios(@RequestParam("file") MultipartFile file){
        List<UsuarioDTOPeticion> peticiones = procesadorArchivos.procesarArchivo(file);
        Map<String, String> erroresPeticiones;
        for(UsuarioDTOPeticion peticion : peticiones) {
            erroresPeticiones = usuarioExcelService.validarUsuario(peticion);
            if(erroresPeticiones != null)
                return new ResponseEntity<Map<String, String>>(erroresPeticiones, HttpStatus.BAD_REQUEST);
        }

        List<Usuario> usuarios = mapper.mapearPeticionesAModelo(peticiones);
        List<Usuario> respuesta;
        try {
            respuesta = casoDeUso.crearUsuarios(usuarios);
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<UsuarioDTORespuesta>>(
                mapper.mapearModelosARespuesta(respuesta), HttpStatus.OK
        );
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable String uuid, @Valid @RequestBody UsuarioActualizarDTOPeticion peticion){
        Usuario usuario;
        try{
            usuario = casoDeUso.actualizarUsuario(uuid, mapper.mapearPeticionActualizarAModelo(peticion));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<UsuarioDTORespuesta>(
                mapper.mapearModeloARespuesta(usuario), HttpStatus.OK
        );
    }
}
