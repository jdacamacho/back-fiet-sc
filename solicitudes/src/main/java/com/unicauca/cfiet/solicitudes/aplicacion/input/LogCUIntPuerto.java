package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.dominio.modelos.Log;
import java.util.List;

/**
 * Interfaz de caso de uso para la gestión de Logs en el sistema.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface LogCUIntPuerto {
    /**
     * Crear un log en el sistema.
     *
     * @param accion la acción realizada.
     * @param resultado el resultado de la acción.
     * @param token el JWT del usuario que realiza la acción.
     */
    void crearLog(String accion, String resultado, String token);

    /**
     * Crear un log de inicio de sesión para un usuario específico.
     *
     * @param accion la acción de la sesión.
     * @param resultado el resultado de la acción.
     * @param username el nombre de usuario de la sesión.
     */
    void crearLogSesion(String accion, String resultado, String username);

    /**
     * Obtener logs paginados.
     *
     * @param pagina el número de página.
     * @param tamanio el tamaño de cada página.
     * @return Lista de logs correspondientes a la página solicitada.
     */
    List<Log> getLogs(int pagina, int tamanio);

    /**
     * Obtener logs filtrados.
     * @param responsable responsable de la acción.
     * @param fecha fecha de creación.
     * @param pagina el número de página.
     * @param tamanio el tamaño de cada página.
     * @return Lista de logs correspondiente a la página solicitada.
     */
    List<Log> getLogs(String responsable, String fecha, int pagina, int tamanio);

    /**
     * Cuenta los logs del sistema.
     *
     * @return Numero de logs en el sistema.
     */
    long countLogs();

    /**
     * Obtener todos los logs del sistema.
     *
     * @return Lista completa de logs.
     */
    List<Log> getLogs();
}
