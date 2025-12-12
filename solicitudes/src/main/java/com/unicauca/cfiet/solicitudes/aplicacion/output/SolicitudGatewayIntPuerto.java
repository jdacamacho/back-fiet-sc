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

    /**
     * Obtener las solicitudes de forma paginada pertenecientes a un funcionario.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista de solicitudes de la página solicitada.
     */
    PaginacionRespuestaDTO<Solicitud> getSolicitudesPorFuncionario(String uuidFuncionario, int pagina, int tamanio);

    /**
     * Obtener todos las solicitudes pertenecientes a un orden del día.
     *
     * @return lista con todos las solicitudes.
     */
    List<Solicitud> getSolicitudesPorOrdenDelDia(String uuidOrdenDelDia);

    /**
     * Obtener todos las solicitudes de acuerdo al estado pasado por parametro.
     *
     * @return lista con todos las solicitudes.
     */
    List<Solicitud> getSolicitudesPorEstado(String estado);

    /**
     * Obtener las solicitudes de forma paginada pertenecientes a un filtro (nombre) establecido.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista de solicitudes de la página solicitada.
     */
    PaginacionRespuestaDTO<Solicitud> buscarSolicitudesPorNombre(String filtro, int pagina, int tamanio);

    /**
     * Obtener las solicitudes de forma paginada pertenecientes a un filtro (nombre) establecido.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista de solicitudes de la página solicitada.
     */
    PaginacionRespuestaDTO<Solicitud> buscarSolicitudesPorNombreYFuncionario(String uuidFuncionario, String filtro, int pagina, int tamanio);
}
