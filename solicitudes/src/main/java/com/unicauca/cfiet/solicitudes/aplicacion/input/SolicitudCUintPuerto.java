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
     * @param token token de autenticación.
     * @return la solicitud creada.
     */
    Solicitud crearSolicitud(Solicitud solicitud, String token);

    /**
     * Crear una nueva solicitud para un perfil publico.
     *
     * @param solicitud Solicitud a crear.
     * @return la solicitud creada.
     */
    Solicitud crearSolicitudPublica(Solicitud solicitud);

    /**
     * Actualizar una solicitud existente.
     *
     * @param uuidSolicitudd el identificador único de la solicitud a actualizar.
     * @param solicitud la información actualizada de la solicitud.
     *  @param token token de autorización
     * @return la solicitud actualizada.
     */
    Solicitud actualizarSolicitud(String uuidSolicitudd, Solicitud solicitud, String token);

    /**
     * Consultar las solicitudes asociadas a un funcionario específico.
     *
     * @param uuidFuncionario Identificador único del funcionario.
     * @param pagina Número de página (0-indexed).
     * @param tamanio Tamaño de cada página.
     * @return Paginación de solicitudes del funcionario.
     */
    PaginacionRespuestaDTO<Solicitud> getSolicitudesPorFuncionario(String uuidFuncionario, int pagina, int tamanio);

    /**
     * Consultar todas las solicitudes asociadas a un Orden del Día específico.
     *
     * @param uuidOrdenDelDia Identificador único del Orden del Día.
     * @return Lista de solicitudes asociadas al Orden del Día.
     */
    List<Solicitud> getSolicitudesPorOrdenDelDia(String uuidOrdenDelDia);

    /**
     * Consultar todas las solicitudes que tengan un estado específico.
     *
     * @param estado Estado de las solicitudes a consultar.
     * @return Lista de solicitudes que coinciden con el estado indicado.
     */
    List<Solicitud> getSolicitudesPorEstado(String estado);

    /**
     * Buscar solicitudes por coincidencia en el nombre (contiene el texto indicado).
     *
     * @param filtro Texto a buscar dentro del nombre de la solicitud.
     * @param pagina Número de página (0-indexed).
     * @param tamanio Tamaño de cada página.
     * @return Paginación de solicitudes que coinciden con el filtro.
     */
    PaginacionRespuestaDTO<Solicitud> buscarSolicitudesPorNombre(String filtro, int pagina, int tamanio);

    /**
     * Buscar solicitudes por coincidencia en el nombre (contiene el texto indicado).
     *
     * @param filtro Texto a buscar dentro del nombre de la solicitud.
     * @param pagina Número de página (0-indexed).
     * @param tamanio Tamaño de cada página.
     * @return Paginación de solicitudes que coinciden con el filtro.
     */
    PaginacionRespuestaDTO<Solicitud> buscarSolicitudesPorNombreYFuncionario(String uuidFuncionario, String filtro, int pagina, int tamanio);
}
