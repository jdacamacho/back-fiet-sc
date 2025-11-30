package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.SolicitudGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.Solicitud;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.SolicitudEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.SolicitudRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class SolicitudGatewayImplAdaptador implements SolicitudGatewayIntPuerto {
    private final SolicitudRepositorio repositorio;
    private final ModelMapper mapper;

    public SolicitudGatewayImplAdaptador(SolicitudRepositorio repositorio,
                                         @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;

    }

    @Override
    public List<Solicitud> getSolicitudes() {
        List<SolicitudEntidad> entidades = repositorio.findAll(Sort.by("fechaCreacion").descending());
        return entidades.stream()
                .map(e -> mapper.map(e, Solicitud.class))
                .toList();
    }

    @Override
    public PaginacionRespuestaDTO<Solicitud> getSolicitudes(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("fechaCreacion").descending());
        Page<SolicitudEntidad> page = repositorio.findAll(paginado);
        List<Solicitud> respuesta = page.getContent().stream()
                .map(e -> mapper.map(e, Solicitud.class))
                .toList();

        return new PaginacionRespuestaDTO<>(respuesta, page.getTotalElements());
    }

    @Override
    public Solicitud getSolicitud(String uuidSolicitud) {
        if(repositorio.existsById(uuidSolicitud)){
            SolicitudEntidad entidad = repositorio.findById(uuidSolicitud).get();
            return mapper.map(entidad, Solicitud.class);
        }
        return null;
    }

    @Override
    public Solicitud guardarSolicitud(Solicitud solicitud) {
        SolicitudEntidad entidadGuardar = mapper.map(solicitud, SolicitudEntidad.class);
        SolicitudEntidad entidadGuardada = repositorio.save(entidadGuardar);
        return mapper.map(entidadGuardada, Solicitud.class);
    }
}
