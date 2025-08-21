package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.RolCUIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.RolDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.RolDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.mapeador.MapperRolInfraestructuraDominio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.application}roles")
@Validated
@RequiredArgsConstructor
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
    public ResponseEntity<RolDTORespuesta> actualizarRol(@PathVariable String uuid, @RequestBody RolDTOPeticion rolPeticion){
        Rol rol = casoDeUso.actualizarRol(uuid, mapper.mapearPeticionAModelo(rolPeticion));
        return new ResponseEntity<RolDTORespuesta>(
                mapper.mapearModeloARespuesta(rol), HttpStatus.OK
        );
    }
}
