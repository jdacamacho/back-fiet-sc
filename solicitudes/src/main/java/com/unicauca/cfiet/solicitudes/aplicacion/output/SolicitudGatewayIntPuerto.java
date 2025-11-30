package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.Solicitud;
import java.util.List;

/**
 * Interfaz que actua como fachada con la capa de persistencia para la gestión de solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface SolicitudGatewayIntPuerto {
    /**
     * Obtener todos las solicitudes almacenados.
     *
     * @return lista con todos las solicitudes.
     */
    List<Solicitud> getSolicitudes();

    /**
     * Obtener las solicitudes de forma paginada.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista de solicitudes de la página solicitada.
     */
    PaginacionRespuestaDTO<Solicitud> getSolicitudes(int pagina, int tamanio);

    /**
     * Buscar una solicitud por su identificador único.
     *
     * @param uuidSolicitud identificador de la solicitud.
     * @return la solicitud correspondiente.
     */
    Solicitud getSolicitud(String uuidSolicitud);

    /**
     * Guardar una solicitud o actualizar uno existente.
     *
     * @param solicitud objeto con la información de la solicitud.
     * @return la solicitud guardado.
     */
    Solicitud guardarSolicitud(Solicitud solicitud);
}
