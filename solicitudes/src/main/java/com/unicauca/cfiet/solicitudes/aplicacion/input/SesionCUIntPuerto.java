package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioTokenizado;

/**
 * Interface de los casos de usos para la gestión de Sesiones.
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
