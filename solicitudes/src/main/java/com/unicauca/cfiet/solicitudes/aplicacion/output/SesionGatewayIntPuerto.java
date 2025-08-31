package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;

/**
 * Interface que actua como fachada con la capa de persistencia para la gestión de sesiones.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface SesionGatewayIntPuerto {
    /**
     * Inicia sesión en el sistema con las credenciales proporcionadas.
     *
     * @param username el nombre de usuario.
     * @param password la contraseña del usuario.
     * @return un token de autenticación si el inicio de sesión es exitoso.
     */
    String login(String username, String password);

    /**
     * Obtiene la información de un usuario a partir de su nombre de usuario.
     *
     * @param username el nombre de usuario.
     * @return el objeto Usuario correspondiente.
     */
    Usuario getUsuario(String username);

}
