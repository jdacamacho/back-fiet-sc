package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorLog.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.LogCUIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Log;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorLog.DTORespuesta.LogDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorLog.mapeador.MapperLogInfraestructuraDominio;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@RestController
@RequestMapping("${url.application}logs")
@Validated
@RequiredArgsConstructor
@Tag(name = "Logs", description = "Operaciones relacionadas con la gestión de logs.")
public class LogRestController {
    private final LogCUIntPuerto casoDeUso;
    private final MapperLogInfraestructuraDominio mapper;
    @Value("${rol.secretarioGeneral}")
    private String rolSecretarioGeneral;

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping
    public ResponseEntity<List<LogDTORespuesta>> index(){
        List<Log> logs = casoDeUso.getLogs();
        return new ResponseEntity<List<LogDTORespuesta>>(
                mapper.mapearModelosARespuesta(logs), HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority(#this.rolSecretarioGeneral)")
    @GetMapping("/paginado")
    public ResponseEntity<List<LogDTORespuesta>> indexPaginado(@RequestParam("pagina") int pagina, @RequestParam("tamanio") int tamanio){
        List<Log> logs = casoDeUso.getLogs(pagina, tamanio);
        return new ResponseEntity<List<LogDTORespuesta>>(
                mapper.mapearModelosARespuesta(logs), HttpStatus.OK
        );
    }
}
