package com.unicauca.cfiet.solicitudes.aplicacion.output;

/**
 * Interface que encripta contraseñas
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface PasswordEncoderGatewayIntPuerto {
    /**
     * Encripta una contraseña utilizando un algoritmo seguro de hashing.
     *
     * @param contraseña la contraseña en texto plano.
     * @return la contraseña encriptada.
     */
    String encriptarContraseña(String contraseña);

    /**
     * Verifica si una contraseña en texto plano coincide con su versión encriptada.
     *
     * @param contraseñaOriginal la contraseña encriptada en la base de datos.
     * @param contraseña la contraseña en texto plano.
     * @return true si coinciden, false en caso contrario.
     */
    boolean contraseñaCoincide(String contraseñaOriginal, String contraseña);
}
