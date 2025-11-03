package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.dominio.modelos.UsuarioTokenizado;

/**
 * Interfaz de caso de uso para la gestión de Sesiones.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface SesionCUIntPuerto {

    /**
     * Iniciar sesión en el sistema.
     *
     * @param username el nombre de usuario.
     * @param contraseña la contraseña del usuario.
     * @return el token de usuario generado si las credenciales son correctas.
     */
    UsuarioTokenizado login(String username, String contraseña);
}
