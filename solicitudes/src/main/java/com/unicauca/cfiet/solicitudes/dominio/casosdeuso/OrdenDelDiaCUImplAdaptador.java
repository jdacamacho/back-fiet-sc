package com.unicauca.cfiet.solicitudes.dominio.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.LogCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.input.OrdenDelDiaCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.OrdenDelDiaGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones.MensajesError;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementación de la interfaz de los casos de uso para la gestión de ordenes del día.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class OrdenDelDiaCUImplAdaptador implements OrdenDelDiaCUIntPuerto {
    private final OrdenDelDiaGatewayIntPuerto gateway;
    private final ExcepcionesFormateadorIntPuerto formateadorExcepciones;
    private final LogCUIntPuerto log;
    /* Constantes */
    private static final String ORDENES_DEL_DIA = "ordenes del día";
    private static final String ORDEN_DEL_DIA = "Orden del Día";

    public OrdenDelDiaCUImplAdaptador(OrdenDelDiaGatewayIntPuerto gateway,
                                      ExcepcionesFormateadorIntPuerto formateadorExcepciones,
                                      LogCUIntPuerto log){
        this.gateway = gateway;
        this.formateadorExcepciones = formateadorExcepciones;
        this.log = log;
    }

    @Override
    public List<OrdenDelDia> getOrdenesDelDia() {
        List<OrdenDelDia> respuesta = gateway.getOrdenesDelDia();
        if(respuesta.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, ORDENES_DEL_DIA));
        return respuesta;
    }

    @Override
    public PaginacionRespuestaDTO<OrdenDelDia> getOrdenesDelDia(int pagina, int tamanio) {
        if (pagina < 0 || tamanio < 0)
            formateadorExcepciones.lanzarMalFormato(MensajesError.PAGINACION_ERROR);
        PaginacionRespuestaDTO<OrdenDelDia> respuesta = gateway.getOrdenesDelDia(pagina, tamanio);
        if (respuesta.getContent().isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, ORDENES_DEL_DIA));
        return respuesta;
    }

    @Override
    public OrdenDelDia getOrdenDelDia(String uuidOrdenDelDia) {
        OrdenDelDia ordenDelDia = gateway.getOrdenDelDia(uuidOrdenDelDia);
        if(ordenDelDia == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, ORDEN_DEL_DIA, uuidOrdenDelDia));
        return ordenDelDia;
    }

    @Override
    public OrdenDelDia crearOrdenDelDia(OrdenDelDia ordenDelDia, String token) {
        ordenDelDia.setUuidOrdenDelDia(UUID.randomUUID().toString());
        ordenDelDia.setEstado(true);
        ordenDelDia.setSolicitudes(new ArrayList<>());
        return gateway.guardarOrdenDelDia(ordenDelDia);
    }

    @Override
    public OrdenDelDia actualizarOrdenDelDia(String uuidOrdenDelDia, OrdenDelDia ordenDelDia, String token) {
        OrdenDelDia ordenDelDiaActualizar = getOrdenDelDia(uuidOrdenDelDia);
        ordenDelDiaActualizar.actualizar(ordenDelDia);
        return gateway.guardarOrdenDelDia(ordenDelDiaActualizar);
    }
}
