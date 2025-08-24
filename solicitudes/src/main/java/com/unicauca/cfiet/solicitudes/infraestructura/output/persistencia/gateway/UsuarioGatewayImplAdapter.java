package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.UsuarioGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.UsuarioLivianoRepositorio;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.UsuarioRepositorio;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class UsuarioGatewayImplAdapter implements UsuarioGatewayIntPuerto {
    private final UsuarioLivianoRepositorio repositorioBasico;
    private final UsuarioRepositorio repositorio;
    private final ModelMapper mapper;

    public UsuarioGatewayImplAdapter(UsuarioLivianoRepositorio repositorioBasico,
                                     UsuarioRepositorio repositorio,
                                     @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorioBasico = repositorioBasico;
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public List<UsuarioLiviano> getUsuarios(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        List<UsuarioLivianoEntidad> entidades = repositorioBasico.findAll(paginado).getContent();
        return mapper.map(entidades, new TypeToken<List<UsuarioLiviano>>(){}.getType());
    }

    @Override
    public Usuario getUsuario(String uuid) {
        if(repositorio.existsById(uuid)) {
            UsuarioEntidad entidad = repositorio.findById(uuid).get();
            if (entidad instanceof FuncionarioEntidad)
                return mapper.map(entidad, Funcionario.class);
            else if (entidad instanceof DecanoEntidad)
                return mapper.map(entidad, Decano.class);
            else if (entidad instanceof SecretarioGeneralEntidad)
                return mapper.map(entidad, SecretarioGeneral.class);
            else
                return mapper.map(entidad, Usuario.class);
        }
        return null;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioEntidad usuarioGuardar;

        if(usuario instanceof SecretarioGeneral)
            usuarioGuardar = mapper.map(usuario, SecretarioGeneralEntidad.class);
        else if (usuario instanceof Funcionario)
            usuarioGuardar = mapper.map(usuario, FuncionarioEntidad.class);
        else if (usuario instanceof Decano)
            usuarioGuardar = mapper.map(usuario, DecanoEntidad.class);
        else
            return null;

        UsuarioEntidad usuarioGuardado = repositorio.save(usuarioGuardar);
        return mapper.map(usuarioGuardado, Usuario.class);
    }

    @Override
    public boolean existeUsuarioNumeroDocumento(String numeroDocumento) {
        return repositorio.existsByNumeroDocumento(numeroDocumento);
    }

    @Override
    public boolean existeUsuarioCorreo(String correo) {
        return repositorio.existsByCorreoElectronico(correo);
    }

    @Override
    public boolean existeUsuarioUsername(String username) {
        return repositorio.existsByUsername(username);
    }

    @Override
    public List<TipoUsuario> getTiposUsuario() {
        List<TipoUsuarioEntidad> entidades = repositorio.findAllTipoUsuario();
        return mapper.map(entidades,  new TypeToken<List<TipoUsuario>>(){}.getType());
    }

    @Override
    public TipoUsuario getTipoUsuarioPorNombre(String nombre) {
        TipoUsuarioEntidad entidad = repositorio.findTipoUsuarioByNombre(nombre).orElse(null);
        if(entidad != null)
            return mapper.map(entidad, TipoUsuario.class);
        return null;
    }
}
