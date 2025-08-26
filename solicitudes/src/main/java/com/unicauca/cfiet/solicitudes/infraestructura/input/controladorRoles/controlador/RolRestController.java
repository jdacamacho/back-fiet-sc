package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.RolCUIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.DTOPeticion.RolDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.DTORespuesta.RolDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.mapeador.MapperRolInfraestructuraDominio;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@RestController
@RequestMapping("${url.application}roles")
@Validated
@RequiredArgsConstructor
@Tag(name = "Roles", description = "Operaciones relacionadas con la gestión de roles.")
public class RolRestController {
    private final RolCUIntPuerto casoDeUso;
    private final MapperRolInfraestructuraDominio mapper;

    @GetMapping
    public ResponseEntity<List<RolDTORespuesta>> index(@RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio){
        List<Rol> roles = casoDeUso.getRoles(pagina, tamanio);
        return new ResponseEntity<List<RolDTORespuesta>>(
                mapper.mapearModelosARespuesta(roles), HttpStatus.OK
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RolDTORespuesta> getRol(@PathVariable String uuid){
        Rol rol = casoDeUso.getRol(uuid);
        return new ResponseEntity<RolDTORespuesta>(
                mapper.mapearModeloARespuesta(rol), HttpStatus.OK
        );
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> actualizarRol(@PathVariable String uuid, @Valid @RequestBody RolDTOPeticion rolPeticion){
        Rol rol;
        try {
             rol = casoDeUso.actualizarRol(uuid, mapper.mapearPeticionAModelo(rolPeticion));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<RolDTORespuesta>(
                mapper.mapearModeloARespuesta(rol), HttpStatus.OK
        );
    }
}
