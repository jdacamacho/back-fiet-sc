package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.*;

import java.util.List;

/**
 * Interfaz que actua como fachada con la capa de persistencia para la gestión de usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface UsuarioGatewayIntPuerto {
    /**
     * Obtiene la lista de usuarios.
     *
     * @return la lista de usuarios livianos.
     */
    List<UsuarioLiviano> getUsuarios();

    /**
     * Obtiene la lista de funcionarios..
     *
     * @return la lista de funcionarios.
     */
    List<Funcionario> getFuncionarios();

    /**
     * Obtiene la lista de usuarios de manera paginada.
     *
     * @param pagina el número de la página a recuperar (empezando desde 0).
     * @param tamanio la cantidad de elementos por página.
     * @return la lista de usuarios livianos correspondientes a la página.
     */
    PaginacionRespuestaDTO<UsuarioLiviano> getUsuarios(int pagina, int tamanio);

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
     * Guarda usuarios en el sistema.
     *
     * @param usuarios la lista de usuarios a guardar
     * @return los usuarios guardados.
     */
    List<Usuario> guardarUsuarios(List<Usuario> usuarios);

    /**
     * Obtiene la lista de usuarios filtrados por nombre y apellido.
     *
     * @param nombreCompleto nomre completo del Usuario
     * @param pagina el número de página.
     * @param tamanio el tamaño de la página.
     * @return la lista de usuarios filtrados.
     */
    PaginacionRespuestaDTO<UsuarioLiviano> getUsuariosByNombreCompleto(String nombreCompleto, int pagina, int tamanio);

    /**
     * Verifica si existe un usuario con el número de documento dado.
     *
     * @param numeroDocumento el número de documento a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    boolean existeUsuarioNumeroDocumento(String numeroDocumento);

    /**
     * Cuenta los usuarios en el sistema.
     *
     * @return el numero de usuarios en el sistema.
     */
    long countUsuarios();

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
