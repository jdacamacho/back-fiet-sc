package com.unicauca.cfiet.solicitudes.aplicacion.input;

import com.unicauca.cfiet.solicitudes.dominio.modelos.TipoSolicitud;
import java.util.List;

/**
 * Interface de los casos de usos para la gestión de Tipos de Solicitud.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface TipoSolicitudCUIntPuerto {
    List<TipoSolicitud> getTiposSolicitud();
    List<TipoSolicitud> getTiposSolicitud(int pagina, int tamanio);
    TipoSolicitud getTipoSolicitud(String uuidTipoSolicitud);
    TipoSolicitud crearTipoSolicitud(TipoSolicitud tipoSolicitud, String token);
    TipoSolicitud actualizarTipoSolicitud(String uuidTipoSolicitud, TipoSolicitud tipoSolicitud, String token);
    List<TipoSolicitud> crearTiposSolicitud(List<TipoSolicitud> tiposSolicitud, String token);
}
