package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.RolGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.modelos.Rol;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.RolEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.RolRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de roles.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class RolGatewayImplAdaptador implements RolGatewayIntPuerto {
    private final RolRepositorio repositorio;
    private final ModelMapper mapper;

    public RolGatewayImplAdaptador(RolRepositorio repositorio, @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public List<Rol> getRoles() {
        List<RolEntidad> entidades = repositorio.findAll();
        return entidades.stream()
                .map(e -> mapper.map(e, Rol.class))
                .toList();
    }

    @Override
    public List<Rol> getRoles(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        List<RolEntidad> entidades = repositorio.findAll(paginado).getContent();
        return entidades.stream()
                .map(e -> mapper.map(e, Rol.class))
                .toList();
    }

    @Override
    public Rol getRol(String uuid) {
        if(repositorio.existsById(uuid)) {
            RolEntidad entidad = repositorio.findById(uuid).get();
            return mapper.map(entidad, Rol.class);
        }
        return null;
    }

    @Override
    public Rol guardarRol(Rol rol) {
        RolEntidad rolGuardar = mapper.map(rol, RolEntidad.class);
        RolEntidad rolGuardado = repositorio.save(rolGuardar);
        return mapper.map(rolGuardado, Rol.class);
    }
}
