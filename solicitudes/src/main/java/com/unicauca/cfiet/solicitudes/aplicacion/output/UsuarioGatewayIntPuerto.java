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
    /**
     * Obtiene la lista de usuarios de manera paginada.
     *
     * @return la lista de usuarios livianos correspondientes a la página.
     */
    List<UsuarioLiviano> getUsuarios();

    /**
     * Obtiene la lista de usuarios de manera paginada.
     *
     * @param pagina el número de la página a recuperar (empezando desde 0).
     * @param tamanio la cantidad de elementos por página.
     * @return la lista de usuarios livianos correspondientes a la página.
     */
    List<UsuarioLiviano> getUsuarios(int pagina, int tamanio);

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param uuidUsuario el identificador único del usuario.
     * @return el objeto Usuario correspondiente.
     */
    Usuario getUsuario(String uuidUsuario);

    /**
     * Guarda un usuario en el sistema.
     *
     * @param usuario el objeto Usuario a guardar.
     * @return el usuario guardado con su información actualizada.
     */
    Usuario guardarUsuario(Usuario usuario);

    /**
     * Verifica si existe un usuario con el número de documento dado.
     *
     * @param numeroDocumento el número de documento a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existeUsuarioNumeroDocumento(String numeroDocumento);

    /**
     * Verifica si existe un usuario con el correo dado.
     *
     * @param correo el correo electrónico a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existeUsuarioCorreo(String correo);

    /**
     * Verifica si existe un usuario con el nombre de usuario dado.
     *
     * @param username el nombre de usuario a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existeUsuarioUsername(String username);

    /**
     * Obtiene la lista de tipos de usuario disponibles.
     *
     * @return la lista de tipos de usuario.
     */
    List<TipoUsuario> getTiposUsuario();

    /**
     * Obtiene un tipo de usuario por su nombre.
     *
     * @param nombre el nombre del tipo de usuario.
     * @return el objeto TipoUsuario correspondiente.
     */
    TipoUsuario getTipoUsuarioPorNombre(String nombre);

    /**
     * Obtiene la lista de roles disponibles.
     *
     * @return la lista de roles.
     */
    List<Rol> getRoles();
}
