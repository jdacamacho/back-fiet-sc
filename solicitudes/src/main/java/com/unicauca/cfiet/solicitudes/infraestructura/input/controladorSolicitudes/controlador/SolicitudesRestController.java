package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.controlador;

import com.unicauca.cfiet.solicitudes.aplicacion.input.OrdenDelDiaCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.input.SolicitudCUintPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import com.unicauca.cfiet.solicitudes.dominio.modelos.Solicitud;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion.OrdenDelDiaDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion.SolicitudActualizarDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion.SolicitudDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion.SolicitudPublicaDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.ValidadorAnexosService;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.mapeador.MapperSolicitudesInfraestructuraDominio;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("${url.application}solicitudes")
@CrossOrigin(origins = "${url.frontend}")
@Validated
@RequiredArgsConstructor
@Tag(name = "Solicitudes", description = "Operaciones relacionadas con la gestión de Solicitudes.")
public class SolicitudesRestController {
    private final OrdenDelDiaCUIntPuerto ordenDelDiaCU;
    private final SolicitudCUintPuerto solicitudCU;
    private final MapperSolicitudesInfraestructuraDominio mapper;
    private final ValidadorAnexosService validadorAnexosService;

    @PreAuthorize("hasAuthority('Secretario General')")
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

    @PreAuthorize("hasAuthority('Secretario General')")
    @GetMapping("/orden-del-dia")
    public ResponseEntity<?> index(){
        List<OrdenDelDia> ordenesDelDia = ordenDelDiaCU.getOrdenesDelDia();
        return ResponseEntity.ok(
                mapper.mapearModelosARespuesta(ordenesDelDia)
        );
    }

    @PreAuthorize("hasAuthority('Secretario General')")
    @GetMapping("/orden-del-dia/{uuidOrdenDelDia}")
    public ResponseEntity<?> getOrdenDelDia(@PathVariable String uuidOrdenDelDia){
        OrdenDelDia ordenDelDia = ordenDelDiaCU.getOrdenDelDia(uuidOrdenDelDia);
        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(ordenDelDia)
        );
    }

    @PreAuthorize("hasAuthority('Secretario General')")
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

    @PreAuthorize("hasAuthority('Secretario General')")
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

    @PreAuthorize("hasAnyAuthority('Secretario General', 'Funcionario')")
    @GetMapping("/paginado")
    public ResponseEntity<?> solicitudesPaginado(
            @RequestParam("pagina") int pagina,
            @RequestParam("tamanio") int tamanio) {
        var respuesta = solicitudCU.getSolicitudes(pagina, tamanio);
        return ResponseEntity.ok(
                new PaginacionRespuestaDTO<>(
                        mapper.mapearModelosARespuestaSolicitud(respuesta.getContent()),
                        respuesta.getTotalElements()
                )
        );
    }

    @PreAuthorize("hasAnyAuthority('Secretario General', 'Funcionario')")
    @GetMapping
    public ResponseEntity<?> solicitudesIndex(){
        List<Solicitud> ordenesDelDia = solicitudCU.getSolicitudes();
        return ResponseEntity.ok(
                mapper.mapearModelosARespuestaSolicitud(ordenesDelDia)
        );
    }

    @PreAuthorize("hasAnyAuthority('Secretario General', 'Funcionario')")
    @GetMapping("/{uuidSolicitud}")
    public ResponseEntity<?> getSolicitud(@PathVariable String uuidSolicitud){
        Solicitud solicitud = solicitudCU.getSolicitud(uuidSolicitud);
        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(solicitud)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> enviarSolicitud(@Valid @RequestPart("solicitud") SolicitudDTOPeticion peticion,
                                             @RequestPart("archivos") List<MultipartFile> archivos,
                                             @RequestHeader("Authorization") String token){

        validadorAnexosService.validarYAsignarArchivos(peticion.getAnexos(), archivos);
        Solicitud solicitud;
        try{
            solicitud = solicitudCU.crearSolicitud(mapper.mapearPeticionAModelo(peticion),  token.substring(7));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(solicitud)
        );
    }

    @Transactional
    @PostMapping(value = "/public", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> enviarSolicitudPublica(@Valid @RequestPart("solicitud") SolicitudPublicaDTOPeticion peticion,
                                                    @RequestPart("archivos") List<MultipartFile> archivos){

        validadorAnexosService.validarYAsignarArchivos(peticion.getAnexos(), archivos);
        Solicitud solicitud;
        try{
            solicitud = solicitudCU.crearSolicitudPublica(mapper.mapearPeticionAModelo(peticion));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(solicitud)
        );
    }

    @PreAuthorize("hasAuthority('Secretario General')")
    @Transactional
    @PutMapping("/{uuidSolicitud}")
    public ResponseEntity<?> actualizarOrdenDelDia(@PathVariable String uuidSolicitud,
                                                   @Valid @RequestBody SolicitudActualizarDTOPeticion peticion,
                                                   @RequestHeader("Authorization") String token){
        Solicitud solicitud;
        try{
            solicitud = solicitudCU.actualizarSolicitud(uuidSolicitud, mapper.mapearPeticionAModelo(peticion), token.substring(7));
        } catch (DataAccessException ex){
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error insertando en la base de datos....");
            response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(
                mapper.mapearModeloARespuesta(solicitud)
        );
    }

    @PreAuthorize("hasAuthority('Funcionario')")
    @GetMapping("/funcionario/{uuidFuncionario}")
    public ResponseEntity<?> getSolicitudesPorFuncionario(
            @PathVariable String uuidFuncionario,
            @RequestParam("pagina") int pagina,
            @RequestParam("tamanio") int tamanio) {

        var respuesta = solicitudCU.getSolicitudesPorFuncionario(uuidFuncionario, pagina, tamanio);
        return ResponseEntity.ok(
                new PaginacionRespuestaDTO<>(
                        mapper.mapearModelosARespuestaSolicitud(respuesta.getContent()),
                        respuesta.getTotalElements()
                )
        );
    }

    @PreAuthorize("hasAuthority('Secretario General')")
    @GetMapping("/orden-del-dia/{uuidOrdenDelDia}/solicitudes")
    public ResponseEntity<?> getSolicitudesPorOrdenDelDia(@PathVariable String uuidOrdenDelDia) {
        List<Solicitud> lista = solicitudCU.getSolicitudesPorOrdenDelDia(uuidOrdenDelDia);
        return ResponseEntity.ok(
                mapper.mapearModelosARespuestaSolicitud(lista)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/estado")
    public ResponseEntity<?> getSolicitudesPorEstado(@RequestParam("estado") String estado) {
        List<Solicitud> lista = solicitudCU.getSolicitudesPorEstado(estado);
        return ResponseEntity.ok(
                mapper.mapearModelosARespuestaSolicitud(lista)
        );
    }

    @PreAuthorize("hasAnyAuthority('Secretario General', 'Funcionario')")
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarSolicitudesPorNombre(
            @RequestParam("filtro") String filtro,
            @RequestParam("pagina") int pagina,
            @RequestParam("tamanio") int tamanio) {

        var respuesta = solicitudCU.buscarSolicitudesPorNombre(filtro, pagina, tamanio);
        return ResponseEntity.ok(
                new PaginacionRespuestaDTO<>(
                        mapper.mapearModelosARespuestaSolicitud(respuesta.getContent()),
                        respuesta.getTotalElements()
                )
        );
    }

    @PreAuthorize("hasAuthority('Secretario General')")
    @GetMapping("/orden-del-dia/buscar")
    public ResponseEntity<?> buscarOrdenDelDiaPorNumeroActa(
            @RequestParam("filtro") String filtro,
            @RequestParam("pagina") int pagina,
            @RequestParam("tamanio") int tamanio) {
        var respuesta = ordenDelDiaCU.buscarOrdenDelDiaPorNumeroActa(filtro, pagina, tamanio);
        return ResponseEntity.ok(
                new PaginacionRespuestaDTO<>(
                        mapper.mapearModelosARespuesta(respuesta.getContent()),
                        respuesta.getTotalElements()
                )
        );
    }


}
