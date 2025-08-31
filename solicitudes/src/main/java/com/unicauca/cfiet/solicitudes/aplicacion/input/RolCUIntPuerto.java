package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;
import java.util.List;

/**
 * Interface de los casos de usos para la gestión de Roles.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface RolCUIntPuerto {
    /**
     * Consultar roles.
     *
     * @param pagina el numero de paginas.
     * @param tamanio el tamanio de las paginas.
     * @return La información de los roles en el sistema, o lanza una excepción en caso de ocurrir un error.
     */
    List<Rol> getRoles(int pagina, int tamanio);

    /**
     * Consultar rol.
     *
     * @param uuidRol Identificador del rol a consultar.
     * @return La información del rol consultado, o lanza una excepción en caso de ocurrir un error.
     */
    Rol getRol(String uuidRol);

    /**
     * Actualiza un rol existente
     *
     * @param uuidRol identificador del rol a modificar.
     * @param rol campos a modificar del rol.
     * @return La información del rol guardado si la operación se realiza con éxito, o lanza una excepción en caso de ocurrir un error.
     */
    Rol actualizarRol(String uuidRol, Rol rol);
}
