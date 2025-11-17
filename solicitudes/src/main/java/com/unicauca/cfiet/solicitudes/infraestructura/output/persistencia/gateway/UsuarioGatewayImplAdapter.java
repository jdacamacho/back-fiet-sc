package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.UsuarioGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.FuncionarioRepositorio;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.UsuarioLivianoRepositorio;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.UsuarioRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementación de la fachada con el servicio de persistencia para la gestión de usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class UsuarioGatewayImplAdapter implements UsuarioGatewayIntPuerto {
    private final UsuarioLivianoRepositorio repositorioBasico;
    private final UsuarioRepositorio repositorio;
    private final FuncionarioRepositorio repositorioFuncionarios;
    private final ModelMapper mapper;

    public UsuarioGatewayImplAdapter(UsuarioLivianoRepositorio repositorioBasico,
                                     UsuarioRepositorio repositorio,
                                     FuncionarioRepositorio repositorioFuncionarios,
                                     @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorioBasico = repositorioBasico;
        this.repositorio = repositorio;
        this.repositorioFuncionarios = repositorioFuncionarios;
        this.mapper = mapper;
    }

    @Override
    public List<UsuarioLiviano> getUsuarios() {
        List<UsuarioLivianoEntidad> entidades = repositorioBasico.findAll();
        return entidades.stream()
                .map(e -> mapper.map(e, UsuarioLiviano.class))
                .toList();
    }

    @Override
    public List<Funcionario> getFuncionarios(){
        List<FuncionarioEntidad> entidades = repositorioFuncionarios.findAll();
        return entidades.stream()
                .map(e -> mapper.map(e, Funcionario.class))
                .toList();
    }

    @Override
    public PaginacionRespuestaDTO<UsuarioLiviano> getUsuarios(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        Page<UsuarioLivianoEntidad> page = repositorioBasico.findAll(paginado);

        List<UsuarioLiviano> usuarios = page.getContent().stream()
                .map(e -> mapper.map(e, UsuarioLiviano.class))
                .toList();

        return new PaginacionRespuestaDTO<>(usuarios, page.getTotalElements());
    }

    @Override
    public Usuario getUsuario(String uuid) {
        if(repositorio.existsById(uuid)) {
            UsuarioEntidad entidad = repositorio.findById(uuid).get();
            if (entidad instanceof FuncionarioEntidad)
                return mapper.map(entidad, Funcionario.class);
            else
                return mapper.map(entidad, Usuario.class);
        }
        return null;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        UsuarioEntidad usuarioGuardar;
        if (usuario instanceof Funcionario)
            usuarioGuardar = mapper.map(usuario, FuncionarioEntidad.class);
        else
            usuarioGuardar = mapper.map(usuario, UsuarioEntidad.class);
        UsuarioEntidad usuarioGuardado = repositorio.save(usuarioGuardar);
        return mapper.map(usuarioGuardado, Usuario.class);
    }

    @Override
    public List<Usuario> guardarUsuarios(List<Usuario> usuarios) {
        List<UsuarioEntidad> entidades = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioEntidad entidad;

            if (usuario instanceof Funcionario)
                entidad = mapper.map(usuario, FuncionarioEntidad.class);
            else
                entidad = mapper.map(usuario, UsuarioEntidad.class);
            entidades.add(entidad);
        }

        List<UsuarioEntidad> guardados = repositorio.saveAll(entidades);
        return guardados.stream()
                .map(e -> {
                    if (e instanceof FuncionarioEntidad)
                        return mapper.map(e, Funcionario.class);
                    else
                        return mapper.map(e, Usuario.class);
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public PaginacionRespuestaDTO<UsuarioLiviano> getUsuariosByNombreCompleto(String nombreCompleto, int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio);
        Page<UsuarioLivianoEntidad> page = repositorioBasico.findByNombreCompleto(nombreCompleto, paginado);

        List<UsuarioLiviano> usuarios = page.getContent().stream()
                .map(e -> mapper.map(e, UsuarioLiviano.class))
                .toList();

        return new PaginacionRespuestaDTO<>(usuarios, page.getTotalElements());
    }

    @Override
    public boolean existeUsuarioNumeroDocumento(String numeroDocumento) {
        return repositorio.existsByNumeroDocumento(numeroDocumento);
    }

    @Override
    public long countUsuarios() {
        return repositorioBasico.countUsuarios();
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
        return entidades.stream()
                .map(e -> mapper.map(e, TipoUsuario.class))
                .toList();
    }

    @Override
    public TipoUsuario getTipoUsuarioPorNombre(String nombre) {
        TipoUsuarioEntidad entidad = repositorio.findTipoUsuarioByNombre(nombre).orElse(null);
        if(entidad != null)
            return mapper.map(entidad, TipoUsuario.class);
        return null;
    }

    @Override
    public List<Rol> getRoles() {
        List<RolEntidad> entidades = repositorio.findAllRoles();
        return entidades.stream()
                .map(e -> mapper.map(e, Rol.class))
                .toList();
    }
}
