package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.LogGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Log;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.LogEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.UsuarioEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.LogRepositorio;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de roles.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class LogGatewayImplAdaptador implements LogGatewayIntPuerto {
    private final LogRepositorio repositorio;
    private final ModelMapper mapper;

    public LogGatewayImplAdaptador(LogRepositorio repositorio, @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Log crearLog(Log log) {
        LogEntidad entidad = mapper.map(log, LogEntidad.class);
        LogEntidad logGuardado = repositorio.save(entidad);
        return mapper.map(logGuardado, Log.class);
    }

    @Override
    public List<Log> getLogs(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        List<LogEntidad> entidades = repositorio.findAll(paginado).getContent();
        return mapper.map(entidades, new TypeToken<List<Log>>(){}.getType());
    }

    @Override
    public List<Log> getLogs() {
        List<LogEntidad> entidades = repositorio.findAll();
        return mapper.map(entidades, new TypeToken<List<Log>>(){}.getType());
    }

    @Override
    public Usuario getUsuarioUsername(String username) {
        Optional<UsuarioEntidad> entidad = repositorio.findUsuarioByUsername(username);
        return entidad.map(usuarioEntidad -> mapper.map(usuarioEntidad, Usuario.class)).orElse(null);
    }
}
