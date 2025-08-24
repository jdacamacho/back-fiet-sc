package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.UsuarioCUIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioLiviano;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.DTOPeticion.RolDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.DTORespuesta.RolDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioActualizarDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioLivianoDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.mapeador.MapperUsuarioInfraestructuraDominio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@RestController
@RequestMapping("${url.application}usuarios")
@Validated
@RequiredArgsConstructor
public class UsuarioRestController {
    private final UsuarioCUIntPuerto casoDeUso;
    private final MapperUsuarioInfraestructuraDominio mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioLivianoDTORespuesta>> index(@RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio){
        List<UsuarioLiviano> usuarios = casoDeUso.getUsuarios(pagina, tamanio);
        return new ResponseEntity<List<UsuarioLivianoDTORespuesta>>(
                mapper.mapearModelosARespuesta(usuarios), HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UsuarioDTORespuesta> getUsuario(@PathVariable String uuid){
        Usuario usuario = casoDeUso.getUsuario(uuid);
        return new ResponseEntity<UsuarioDTORespuesta>(
                mapper.mapearModeloARespuesta(usuario), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioDTORespuesta> crearUsuario(@RequestBody UsuarioDTOPeticion peticion, @RequestParam String tipoUsuario){
        Usuario usuario = casoDeUso.crearUsuario(mapper.mapearPeticionAModelo(peticion), tipoUsuario);
        return new ResponseEntity<UsuarioDTORespuesta>(
                mapper.mapearModeloARespuesta(usuario), HttpStatus.OK
        );
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UsuarioDTORespuesta> actualizarUsuario(@PathVariable String uuid, @RequestBody UsuarioActualizarDTOPeticion peticion){
        Usuario usuario = casoDeUso.actualizarUsuario(uuid, mapper.mapearPeticionActualizarAModelo(peticion));
        return new ResponseEntity<UsuarioDTORespuesta>(
                mapper.mapearModeloARespuesta(usuario), HttpStatus.OK
        );
    }
}
