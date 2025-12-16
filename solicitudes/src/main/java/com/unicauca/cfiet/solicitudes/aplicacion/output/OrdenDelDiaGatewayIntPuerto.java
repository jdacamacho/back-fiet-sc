package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import java.util.List;

/**
 * Interfaz que actua como fachada con la capa de persistencia para la gestión del Orden del Día.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface OrdenDelDiaGatewayIntPuerto {
    /**
     * Obtener todos los ordenes del día almacenados.
     *
     * @return lista con todos los ordenes del día.
     */
    List<OrdenDelDia> getOrdenesDelDia();

    /**
     * Obtener todos los ordenes del día almacenados.
     *
     * @return lista con todos los ordenes del día.
     */
    List<OrdenDelDia> getOrdenesDelDiaPorEstado(boolean estado);

    /**
     * Obtener los ordenes del día de forma paginada.
     *
     * @param pagina número de la página a consultar.
     * @param tamanio cantidad de elementos por página.
     * @return lista con los ordenes del día de la página solicitada.
     */
    PaginacionRespuestaDTO<OrdenDelDia> getOrdenesDelDia(int pagina, int tamanio);

    /**
     * Buscar un orden del día por su identificador único.
     *
     * @param uuidOrdenDelDia identificador del orden del día.
     * @return el orden del día correspondiente.
     */
    OrdenDelDia getOrdenDelDia(String uuidOrdenDelDia);

    /**
     * Guardar un nuevo orden del día o actualizar uno existente.
     *
     * @param ordenDelDia objeto con la información del orden del día.
     * @return el orden del día guardado.
     */
    OrdenDelDia guardarOrdenDelDia(OrdenDelDia ordenDelDia);

    PaginacionRespuestaDTO<OrdenDelDia> getOrdenesDelDia(String filtro, int pagina, int tamanio);
}
