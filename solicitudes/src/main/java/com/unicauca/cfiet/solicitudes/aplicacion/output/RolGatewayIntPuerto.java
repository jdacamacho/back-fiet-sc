package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;

import java.util.List;

/**
 * Interface que actua como fachada con la capa de persistencia
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface RolGatewayIntPuerto {
    /**
     * Consultar roles.
     *
     * @param pagina el numero de paginas.
     * @param tamanio el tamanio de las paginas.
     * @return La información de los roles en el sistema, o lanza una excepción en caso de ocurrir un error.
     */
    public List<Rol> getRoles(int pagina, int tamanio);

    /**
     * Consultar rol.
     *
     * @param uuid Identificador del rol a consultar.
     * @return La información del rol consultado, o lanza una excepción en caso de ocurrir un error.
     */
    public Rol getRol(String uuid);

    /**
     * Actualiza un rol existente
     *
     * @param rol El rol a guardar.
     * @return La información del rol guardado si la operación se realiza con éxito, o lanza una excepción en caso de ocurrir un error.
     */
    public Rol guardarRol(Rol rol);
}
