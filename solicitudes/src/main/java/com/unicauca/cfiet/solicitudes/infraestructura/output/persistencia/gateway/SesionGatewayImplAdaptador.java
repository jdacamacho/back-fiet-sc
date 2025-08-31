package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.gateway;

import com.unicauca.cfiet.solicitudes.aplicacion.output.SesionGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.infraestructura.configuracion.seguridad.jwt.JwtServicio;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.UsuarioEntidad;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios.UsuarioRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Interface que actua como fachada con la capa de persistencia para la gestión de sesiones.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class SesionGatewayImplAdaptador implements SesionGatewayIntPuerto {
    private final UsuarioRepositorio repositorio;
    private final JwtServicio jwtServicio;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;

    public SesionGatewayImplAdaptador(UsuarioRepositorio repositorio,
                                      JwtServicio jwtServicio,
                                      AuthenticationManager authenticationManager,
                                      @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.repositorio = repositorio;
        this.jwtServicio = jwtServicio;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
    }
    @Override
    public String login(String username, String password) {
        Optional<UsuarioEntidad> usuario = repositorio.findByUsername(username);
        if(usuario.isPresent()){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if(authentication.isAuthenticated())
                return jwtServicio.getToken(usuario.get());
        }
        return null;
    }

    @Override
    public Usuario getUsuario(String username) {
        Optional<UsuarioEntidad> usuario = repositorio.findByUsername(username);
        return usuario.map(usuarioEntidad -> mapper.map(usuarioEntidad, Usuario.class)).orElse(null);
    }
}
