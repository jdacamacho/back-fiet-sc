package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.dominio.modelos.TipoSolicitud;
import java.util.List;

/**
 * Interfaz que actua como fachada con la capa de persistencia para la gestión de Tipos de Solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface TipoSolicitudGatewayIntPuerto {

    /**
     * Obtener todos los tipos de solicitud almacenados.
     *
     * @return lista con todos los tipos de solicitud.
     */
    List<TipoSolicitud> getTiposSolicitudes();

    /**
     * Obtener los tipos de solicitud de forma paginada.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista con los tipos de solicitud de la página solicitada.
     */
    List<TipoSolicitud> getTiposSolicitudes(int pagina, int tamanio);

    /**
     * Buscar un tipo de solicitud por su identificador único.
     *
     * @param uuidTipoSolicitud identificador del tipo de solicitud.
     * @return el tipo de solicitud correspondiente.
     */
    TipoSolicitud getTipoSolicitud(String uuidTipoSolicitud);

    /**
     * Guardar un nuevo tipo de solicitud o actualizar uno existente.
     *
     * @param tipoSolicitud objeto con la información del tipo de solicitud.
     * @return el tipo de solicitud guardado.
     */
    TipoSolicitud guardarTipoSolicitud(TipoSolicitud tipoSolicitud);

    /**
     * Guardar varios tipos de solicitud en una sola operación.
     *
     * @param tiposSolicitud lista con los tipos de solicitud a guardar.
     * @return lista con los tipos de solicitud guardados.
     */
    List<TipoSolicitud> guardarTiposSolicitud(List<TipoSolicitud> tiposSolicitud);
}
