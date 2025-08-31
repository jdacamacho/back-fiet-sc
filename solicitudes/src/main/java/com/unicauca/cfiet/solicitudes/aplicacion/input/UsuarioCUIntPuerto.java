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
    /**
     * Consultar lista de usuarios.
     *
     * @return la lista de usuarios en formato liviano.
     */
    List<UsuarioLiviano> getUsuarios();

    /**
     * Consultar lista de usuarios.
     *
     * @param pagina el número de página.
     * @param tamanio el tamaño de la página.
     * @return la lista de usuarios en formato liviano.
     */
    List<UsuarioLiviano> getUsuarios(int pagina, int tamanio);

    /**
     * Consultar un usuario por su identificador.
     *
     * @param uuidUsuario identificador único del usuario.
     * @return la información del usuario.
     */
    Usuario getUsuario(String uuidUsuario);

    /**
     * Crear un nuevo usuario.
     *
     * @param usuario la información del usuario a crear.
     * @param tipoUsuario el tipo de usuario a crear (Decano, Secretario General, Funcionario, Secretaria Decanatura Fiet)
     * @param token token de autorización
     * @return el usuario creado.
     */
    Usuario crearUsuario(Usuario usuario, String tipoUsuario, String token);

    /**
     * Crear múltiples usuarios.
     *
     * @param usuarios la lista de usuarios a crear (obtenida del archivo excel).
     * @param token token de autorización
     * @return la lista de usuarios creados.
     */
    List<Usuario> crearUsuarios(List<Usuario> usuarios, String token);

    /**
     * Actualizar un usuario existente.
     *
     * @param uuidUsuario el identificador único del usuario a actualizar.
     * @param usuario la información actualizada del usuario.
     *  @param token token de autorización
     * @return el usuario actualizado.
     */
    Usuario actualizarUsuario(String uuidUsuario, Usuario usuario, String token);

    /**
     * Cambiar la contraseña de un usuario.
     *
     * @param uuidUsuario el identificador único del usuario.
     * @param contraseña la contraseña actual del usuario.
     * @param nuevaContraseña la nueva contraseña a establecer.
     * @param token token de autorización
     * @return el usuario con la contraseña actualizada.
     */
    Usuario cambiarContraseña(String uuidUsuario, String contraseña, String nuevaContraseña, String token);
}
