package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.OrdenDelDiaCUIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion.OrdenDelDiaDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.mapeador.MapperSolicitudesInfraestructuraDominio;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("${url.application}solicitudes")
@CrossOrigin(origins = "${url.frontend}")
@Validated
@RequiredArgsConstructor
@Tag(name = "Solicitudes", description = "Operaciones relacionadas con la gestión de Solicitudes.")
public class SolicitudesRestController {
    private final OrdenDelDiaCUIntPuerto ordenDelDiaCU;
    private final MapperSolicitudesInfraestructuraDominio mapper;

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping("/orden-del-dia/paginado")
    public ResponseEntity<?> indexPaginado(
            @RequestParam("pagina") int pagina,
            @RequestParam("tamanio") int tamanio) {
        var respuesta = ordenDelDiaCU.getOrdenesDelDia(pagina, tamanio);
        return ResponseEntity.ok(
                new PaginacionRespuestaDTO<>(
                        mapper.mapearModelosARespuesta(respuesta.getContent()),
                        respuesta.getTotalElements()
                )
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping("/orden-del-dia")
    public ResponseEntity<?> index(){
        List<OrdenDelDia> ordenesDelDia = ordenDelDiaCU.getOrdenesDelDia();
        return ResponseEntity.ok(
                mapper.mapearModelosARespuesta(ordenesDelDia)
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping("/orden-del-dia/{uuidOrdenDelDia}")
    public ResponseEntity<?> getOrdenDelDia(@PathVariable String uuidOrdenDelDia){
        OrdenDelDia ordenDelDia = ordenDelDiaCU.getOrdenDelDia(uuidOrdenDelDia);
        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(ordenDelDia)
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @Transactional
    @PostMapping("/orden-del-dia")
    public ResponseEntity<?> crearOrdenDelDia(@Valid @RequestBody OrdenDelDiaDTOPeticion peticion,
                                                @RequestHeader("Authorization") String token){
        OrdenDelDia ordenDelDia;
        try{
            ordenDelDia = ordenDelDiaCU.crearOrdenDelDia(mapper.mapearPeticionAModelo(peticion), token.substring(7));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(ordenDelDia)
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @Transactional
    @PutMapping("/orden-del-dia/{uuidOrdenDelDia}")
    public ResponseEntity<?> actualizarOrdenDelDia(@PathVariable String uuidOrdenDelDia,
                                                   @Valid @RequestBody OrdenDelDiaDTOPeticion peticion,
                                                   @RequestHeader("Authorization") String token){
        OrdenDelDia ordenDelDia;
        try{
            ordenDelDia = ordenDelDiaCU.actualizarOrdenDelDia (uuidOrdenDelDia, mapper.mapearPeticionAModelo(peticion), token.substring(7));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(ordenDelDia)
        );
    }
}
