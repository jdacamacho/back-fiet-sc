package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.Solicitud;
import java.util.List;

/**
 * Interfaz de caso de uso para la gestión de Solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface SolicitudCUintPuerto {
    /**
     * Consultar lista de solicitudes.
     *
     * @return la lista de solicitudes.
     */
    List<Solicitud> getSolicitudes();

    /**
     * Consultar lista de solicitudes.
     *
     * @param pagina el número de página.
     * @param tamanio el tamaño de la página.
     * @return la lista de solicitudes.
     */
    PaginacionRespuestaDTO<Solicitud> getSolicitudes(int pagina, int tamanio);

    /**
     * Consultar una solicitud por su identificador.
     *
     * @param uuidSolicitud identificador único de la solicitud.
     * @return la información de la solicitud.
     */
    Solicitud getSolicitud(String uuidSolicitud);

    /**
     * Crear una nueva solicitud.
     *
     * @param solicitud Solicitud a crear.
     * @return la solicitud creada.
     */
    Solicitud crearSolicitud(Solicitud solicitud);

    /**
     * Actualizar una solicitud existente.
     *
     * @param uuidSolicitudd el identificador único de la solicitud a actualizar.
     * @param solicitud la información actualizada de la solicitud.
     *  @param token token de autorización
     * @return la solicitud actualizada.
     */
    Solicitud actualizarSolicitud(String uuidSolicitudd, Solicitud solicitud, String token);
}
