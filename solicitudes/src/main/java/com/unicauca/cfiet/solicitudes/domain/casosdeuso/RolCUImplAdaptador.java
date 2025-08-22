package com.unicauca.cfiet.solicitudes.domain.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.RolCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.RolGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Rol;

import java.util.List;

/**
 * Implementación de la interfaz de los casos de uso.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class RolCUImplAdaptador implements RolCUIntPuerto {
    private final RolGatewayIntPuerto gateway;
    private  final ExcepcionesFormateadorIntPuerto formateadorExcepciones;

    public RolCUImplAdaptador(RolGatewayIntPuerto gateway, ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        this.gateway = gateway;
        this.formateadorExcepciones = formateadorExcepciones;
    }

    @Override
    public List<Rol> getRoles(int pagina, int tamanio) {
        if(pagina < 0 && tamanio < 0)
            formateadorExcepciones.lanzarMalFormato("Error en la paginación o tamaño de la pagina...");
        List<Rol> roles = gateway.getRoles(pagina, tamanio);
        if(roles.isEmpty())
            formateadorExcepciones.lanzarSinInformacion("No existen registrados roles en el sistema...");
        return  roles;
    }

    @Override
    public Rol getRol(String uuid) {
        Rol rol = gateway.getRol(uuid);
        if(rol == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format("Rol con id %s no fue encontrado en el sistema...", uuid));
        return rol;
    }

    @Override
    public Rol actualizarRol(String uuid, Rol rol) {
        Rol rolObtenido = gateway.getRol(uuid);
        if(rolObtenido == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format("Rol con id %s no fue encontrado en el sistema....", uuid));

        if(rol.getDescripcion() != null && !rol.getDescripcion().isBlank())
            rolObtenido.setDescripcion(rol.getDescripcion());

        if(rol.getEstado() != null)
            rolObtenido.setEstado(rol.getEstado());

        return gateway.guardarRol(rolObtenido);
    }
}
