package com.unicauca.cfiet.solicitudes.aplicacion.output;

import com.unicauca.cfiet.solicitudes.dominio.modelos.TipoSolicitud;
import java.util.List;

/**
 * Interface que actua como fachada con la capa de persistencia para la gestión de tipos de solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface TipoSolicitudGatewayIntPuerto {
    List<TipoSolicitud> getTiposSolicitudes();
    List<TipoSolicitud> getTiposSolicitudes(int pagina, int tamanio);
    TipoSolicitud getTipoSolicitud(String uuidTipoSolicitud);
    TipoSolicitud guardarTipoSolicitud(TipoSolicitud tipoSolicitud);
    List<TipoSolicitud> guardarTiposSolicitud(List<TipoSolicitud> tiposSolicitud);
}
