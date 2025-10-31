package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.TipoSolicitudCUIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.modelos.TipoSolicitud;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.DTOPeticion.TipoSolicitudDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.DTORespuesta.TipoSolicitudDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.mapeador.MapperTipoSolicitudInfraestructuraDominio;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@RestController
@RequestMapping("${url.application}tipos/solicitudes")
@CrossOrigin(origins = "${url.frontend}")
@Validated
@Tag(name = "Tipos Solicitudes", description = "Operaciones relacionadas con la gestión de Tipos de Solicitudes.")
public class TiposSolicitudRestController {
    private final TipoSolicitudCUIntPuerto casoDeUso;
    private final MapperTipoSolicitudInfraestructuraDominio mapper;

    public TiposSolicitudRestController(TipoSolicitudCUIntPuerto casoDeUso,
                                 MapperTipoSolicitudInfraestructuraDominio mapper){
        this.casoDeUso = casoDeUso;
        this.mapper = mapper;
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping("/paginado")
    public ResponseEntity<?> indexPaginado(@RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio){
        List<TipoSolicitud> tipos = casoDeUso.getTiposSolicitud(pagina, tamanio);
        return new ResponseEntity<List<TipoSolicitudDTORespuesta>>(
                mapper.mapearModelosARespuesta(tipos), HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping
    public ResponseEntity<?> index(){
        List<TipoSolicitud> tipos = casoDeUso.getTiposSolicitud();
        return new ResponseEntity<List<TipoSolicitudDTORespuesta>>(
                mapper.mapearModelosARespuesta(tipos), HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping("/{uuidTipoSolicitud}")
    public ResponseEntity<?> getTipoSolicitud(@PathVariable String uuidTipoSolicitud){
        TipoSolicitud tipo = casoDeUso.getTipoSolicitud(uuidTipoSolicitud);
        return new ResponseEntity<TipoSolicitudDTORespuesta>(
                mapper.mapearModeloARespuesta(tipo), HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @Transactional
    @PostMapping
    public ResponseEntity<?> crearTipoSolicitud(@Valid @RequestBody TipoSolicitudDTOPeticion peticion,
                                                @RequestHeader("Authorization") String token){
        TipoSolicitud tipo;
        try{
            tipo = casoDeUso.crearTipoSolicitud(mapper.mapearPeticionAModelo(peticion), peticion.getUuidFuncionario(), token.substring(7));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TipoSolicitudDTORespuesta>(
                mapper.mapearModeloARespuesta(tipo), HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @Transactional
    @PutMapping("/{uuidTipoSolicitud}")
    public ResponseEntity<?> actualizarTipoSolicitud(@PathVariable String uuidTipoSolicitud, @Valid @RequestBody TipoSolicitudDTOPeticion peticion,
                                               @RequestHeader("Authorization") String token){
        TipoSolicitud tipo;
        try{
            tipo = casoDeUso.actualizarTipoSolicitud(uuidTipoSolicitud,  peticion.getUuidFuncionario(),
                    mapper.mapearPeticionAModelo(peticion), token.substring(7));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<TipoSolicitudDTORespuesta>(
                mapper.mapearModeloARespuesta(tipo), HttpStatus.OK
        );
    }
}
