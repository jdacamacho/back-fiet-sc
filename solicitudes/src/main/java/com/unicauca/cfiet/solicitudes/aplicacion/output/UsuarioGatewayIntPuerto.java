package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;
import com.unicauca.cfiet.solicitudes.domain.modelos.TipoUsuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioLiviano;

import java.util.List;

/**
 * Interface que actua como fachada con la capa de persistencia para la gestión de usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface UsuarioGatewayIntPuerto {
    public List<UsuarioLiviano> getUsuarios(int pagina, int tamanio);
    public Usuario getUsuario(String uuid);
    public Usuario guardarUsuario(Usuario usuario);
    public boolean existeUsuarioNumeroDocumento(String numeroDocumento);
    public boolean existeUsuarioCorreo(String correo);
    public boolean existeUsuarioUsername(String username);
    public List<TipoUsuario> getTiposUsuario();
    public TipoUsuario getTipoUsuarioPorNombre(String nombre);
    public List<Rol> getRoles();
}
