package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.domain.modelos.Log;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;


import java.util.List;

/**
 * Interface que actua como fachada con la capa de persistencia para la gestión de logs.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface LogGatewayIntPuerto {
    /**
     * Crear un log en el sistema.
     *
     * @param log el objeto Log a crear.
     * @return El log creado en el sistema.
     */
    Log crearLog(Log log);

    /**
     * Obtener logs paginados.
     *
     * @param pagina el número de página.
     * @param tamanio el tamaño de cada página.
     * @return Lista de logs correspondiente a la página solicitada.
     */
    List<Log> getLogs(int pagina, int tamanio);

    /**
     * Obtener todos los logs del sistema.
     *
     * @return Lista completa de logs.
     */
    List<Log> getLogs();

    /**
     * Obtener un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario.
     * @return El usuario correspondiente al username proporcionado.
     */
    Usuario getUsuarioUsername(String username);
}
