package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioLiviano;
import java.util.List;

/**
 * Interface de los casos de usos para la gestión de usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface UsuarioCUIntPuerto {
    public List<UsuarioLiviano> getUsuarios(int pagina, int tamanio);
    public Usuario getUsuario(String uuid);
    public Usuario crearUsuario(Usuario usuario, String tipoUsuario);
    public List<Usuario> crearUsuarios(Usuario usuario);
    public Usuario actualizarUsuario(String uuid, Usuario usuario);
}
