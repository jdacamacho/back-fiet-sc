package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.TipoSolicitud;
import java.util.List;

/**
 * Interfaz de caso de uso para la gestión de Tipos de Solicitud.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface TipoSolicitudCUIntPuerto {

    /**
     * Obtener todos los tipos de solicitud registrados.
     *
     * @return lista con todos los tipos de solicitud.
     */
    List<TipoSolicitud> getTiposSolicitud();

    /**
     * Obtener los tipos de solicitud de forma paginada.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista con los tipos de solicitud de la página solicitada.
     */
    PaginacionRespuestaDTO<TipoSolicitud> getTiposSolicitud(int pagina, int tamanio);

    /**
     * Obtener los tipos de solicitud filtrados de forma paginada.
     * @param nombreSolicitud nombre del Tipo de Solicitud.
     * @param funcionario nombre del funcionario (nombres o apellidos).
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista con los tipos de solicitud de la página solicitada.
     */
    PaginacionRespuestaDTO<TipoSolicitud> getTiposSolicitud(String nombreSolicitud, String funcionario, int pagina, int tamanio);

    /**
     * Buscar un tipo de solicitud por su identificador único.
     *
     * @param uuidTipoSolicitud identificador del tipo de solicitud.
     * @return el tipo de solicitud correspondiente.
     */
    TipoSolicitud getTipoSolicitud(String uuidTipoSolicitud);

    /**
     * Crear un nuevo tipo de solicitud.
     *
     * @param tipoSolicitud objeto con la información del tipo de solicitud.
     * @param token token del usuario que realiza la acción.
     * @return el tipo de solicitud creado.
     */
    TipoSolicitud crearTipoSolicitud(TipoSolicitud tipoSolicitud, String token);

    /**
     * Actualizar un tipo de solicitud existente.
     *
     * @param uuidTipoSolicitud identificador del tipo de solicitud a actualizar.
     * @param tipoSolicitud objeto con la nueva información del tipo de solicitud.
     * @param token token del usuario que realiza la acción.
     * @return el tipo de solicitud actualizado.
     */
    TipoSolicitud actualizarTipoSolicitud(String uuidTipoSolicitud, TipoSolicitud tipoSolicitud, String token);

    /**
     * Crear varios tipos de solicitud en una sola operación.
     *
     * @param tiposSolicitud lista con los tipos de solicitud a crear.
     * @param token token del usuario que realiza la acción.
     * @return lista con los tipos de solicitud creados.
     */
    List<TipoSolicitud> crearTiposSolicitud(List<TipoSolicitud> tiposSolicitud, String token);

    List<TipoSolicitud> getTiposSolicitudesPorPerfil(String perfil);

    PaginacionRespuestaDTO<TipoSolicitud> getTiposSolicitudesPorPerfilSolicitante(String perfil, int pagina, int tamanio);

    PaginacionRespuestaDTO<TipoSolicitud> getTiposSolicitudesPorNombreYPerfilSolicitante(String nombre, String perfil, int pagina, int tamanio);
}
