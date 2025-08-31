package com.unicauca.cfiet.solicitudes.aplicacion.output;

/**
 * Interface para interacción con la seguridad de spring boot.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface IJwtServicio {
    /**
     * Obtener el nombre de usuario a partir de un token.
     *
     * @param token el JWT del usuario.
     * @return El username contenido en el token.
     */
    String getUsername(String token);
}
