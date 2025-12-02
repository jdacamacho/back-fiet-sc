package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.OrdenDelDiaGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.OrdenDelDiaEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.OrdenDelDiaRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de ordenes del día.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class OrdenDelDiaGatewayImplAdaptador implements OrdenDelDiaGatewayIntPuerto {
    private final OrdenDelDiaRepositorio repositorio;
    private final ModelMapper mapper;

    public OrdenDelDiaGatewayImplAdaptador(OrdenDelDiaRepositorio repositorio,
                                           @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public List<OrdenDelDia> getOrdenesDelDia() {
        List<OrdenDelDiaEntidad> entidades = repositorio.findAll(Sort.by("fechaCreacion").descending());
        return entidades.stream()
                .map(e -> mapper.map(e, OrdenDelDia.class))
                .toList();
    }

    @Override
    public PaginacionRespuestaDTO<OrdenDelDia> getOrdenesDelDia(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("fechaCreacion").descending());
        Page<OrdenDelDiaEntidad> page = repositorio.findAll(paginado);
        List<OrdenDelDia> respuesta = page.getContent().stream()
                .map(e -> mapper.map(e, OrdenDelDia.class))
                .toList();

        return new PaginacionRespuestaDTO<>(respuesta, page.getTotalElements());
    }

    @Override
    public OrdenDelDia getOrdenDelDia(String uuidOrdenDelDia) {
        if(repositorio.existsById(uuidOrdenDelDia)){
            OrdenDelDiaEntidad entidad = repositorio.findById(uuidOrdenDelDia).get();
            return mapper.map(entidad, OrdenDelDia.class);
        }
        return null;
    }

    @Override
    public OrdenDelDia guardarOrdenDelDia(OrdenDelDia ordenDelDia) {
        OrdenDelDiaEntidad entidadGuardar = mapper.map(ordenDelDia, OrdenDelDiaEntidad.class);
        OrdenDelDiaEntidad entidadGuardada = repositorio.save(entidadGuardar);
        return mapper.map(entidadGuardada, OrdenDelDia.class);
    }

    @Override
    public PaginacionRespuestaDTO<OrdenDelDia> getOrdenesDelDia(String filtro, int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        var page = repositorio.findByNumeroActaContainingIgnoreCase(filtro, paginado);

        List<OrdenDelDia> lista = page.getContent().stream()
                .map(entidad -> mapper.map(entidad, OrdenDelDia.class))
                .toList();

        return new PaginacionRespuestaDTO<>(lista, page.getTotalElements());
    }
}
