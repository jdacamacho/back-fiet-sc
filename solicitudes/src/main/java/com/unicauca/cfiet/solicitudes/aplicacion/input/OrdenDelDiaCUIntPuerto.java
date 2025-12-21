package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import java.util.List;

/**
 * Interfaz de caso de uso para la gestión de Ordenes del Día.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface OrdenDelDiaCUIntPuerto {
    /**
     * Consultar lista de ordendes del día.
     *
     * @return la lista de ordenes del día.
     */
    List<OrdenDelDia> getOrdenesDelDia();

    List<OrdenDelDia> getOrdenesDelDiaPorEstado(boolean estado);

    /**
     * Consultar lista de ordendes del día.
     *
     * @param pagina el número de página.
     * @param tamanio el tamaño de la página.
     * @return la lista de ordenes del día.
     */
    PaginacionRespuestaDTO<OrdenDelDia> getOrdenesDelDia(int pagina, int tamanio);

    /**
     * Consultar un orden del día por su identificador.
     *
     * @param uuidOrdenDelDia identificador único del orden del día.
     * @return la información del orden del día.
     */
    OrdenDelDia getOrdenDelDia(String uuidOrdenDelDia);

    /**
     * Crear un nuevo orden del día.
     *
     * @param ordenDelDia orden del día a crear.
     * @param token token de autorización
     * @return el orden del día creado.
     */
    OrdenDelDia crearOrdenDelDia(OrdenDelDia ordenDelDia, String token);

    /**
     * Actualizar un orden del día existente.
     *
     * @param uuidOrdenDelDia el identificador único del orden del día a actualizar.
     * @param ordenDelDia la información actualizada del orden del día.
     *  @param token token de autorización
     * @return el orden del día actualizado.
     */
    OrdenDelDia actualizarOrdenDelDia(String uuidOrdenDelDia, OrdenDelDia ordenDelDia, String token);

    PaginacionRespuestaDTO<OrdenDelDia> buscarOrdenDelDiaPorNumeroActa(String nombre, int pagina, int tamanio);

    byte[] generarOrdenDelDia(String uuidOrden);
}
