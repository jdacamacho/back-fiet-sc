package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.TipoSolicitudGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.modelos.TipoSolicitud;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.TipoSolicitudEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.TipoSolicitudRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de tipos de solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class TipoSolicitudGatewayImplAdaptador implements TipoSolicitudGatewayIntPuerto {
    private final TipoSolicitudRepositorio repositorio;
    private final ModelMapper mapper;

    public TipoSolicitudGatewayImplAdaptador(TipoSolicitudRepositorio repositorio,
                                     @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public List<TipoSolicitud> getTiposSolicitudes() {
        List<TipoSolicitudEntidad> entidades = repositorio.findAll();
        return entidades.stream()
                .map(e -> mapper.map(e, TipoSolicitud.class))
                .toList();
    }

    @Override
    public List<TipoSolicitud> getTiposSolicitudes(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        List<TipoSolicitudEntidad> entidades = repositorio.findAll(paginado).getContent();
        return entidades.stream()
                .map(e -> mapper.map(e, TipoSolicitud.class))
                .toList();
    }

    @Override
    public TipoSolicitud getTipoSolicitud(String uuidTipoSolicitud) {
        if(repositorio.existsById(uuidTipoSolicitud)){
            TipoSolicitudEntidad entidad = repositorio.findById(uuidTipoSolicitud).get();
            return mapper.map(entidad, TipoSolicitud.class);
        }
        return null;
    }

    @Override
    public TipoSolicitud guardarTipoSolicitud(TipoSolicitud tipoSolicitud) {
        TipoSolicitudEntidad entidad = mapper.map(tipoSolicitud, TipoSolicitudEntidad.class);
        TipoSolicitudEntidad entidadGuardada = repositorio.save(entidad);
        return mapper.map(entidadGuardada, TipoSolicitud.class);
    }

    @Override
    public List<TipoSolicitud> guardarTiposSolicitud(List<TipoSolicitud> tiposSolicitud) {
        List<TipoSolicitudEntidad> entidades = tiposSolicitud.stream()
                .map(ts -> mapper.map(ts, TipoSolicitudEntidad.class))
                .toList();
        List<TipoSolicitudEntidad> guardados = repositorio.saveAll(entidades);
        return guardados.stream()
                .map(e -> mapper.map(e, TipoSolicitud.class))
                .toList();
    }
}
