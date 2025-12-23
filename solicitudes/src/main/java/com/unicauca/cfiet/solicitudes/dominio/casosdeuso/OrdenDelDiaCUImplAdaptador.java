package com.unicauca.cfiet.solicitudes.dominio.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.LogCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.input.OrdenDelDiaCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.OrdenDelDiaExportador;
import com.unicauca.cfiet.solicitudes.aplicacion.output.OrdenDelDiaGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.SolicitudGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.helper.constantes.ApplicationConstantes;
import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import com.unicauca.cfiet.solicitudes.dominio.modelos.Solicitud;
import com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones.MensajesError;
import java.util.*;

/**
 * Implementación de la interfaz de los casos de uso para la gestión de ordenes del día.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class OrdenDelDiaCUImplAdaptador implements OrdenDelDiaCUIntPuerto {
    private final OrdenDelDiaGatewayIntPuerto gateway;
    private final ExcepcionesFormateadorIntPuerto formateadorExcepciones;
    private final LogCUIntPuerto log;
    private final OrdenDelDiaExportador exportador;
    private final SolicitudGatewayIntPuerto gatewaySolicitud;
    /* Constantes */
    private static final String ORDENES_DEL_DIA = "ordenes del día";
    private static final String ORDEN_DEL_DIA = "Orden del Día";

    public OrdenDelDiaCUImplAdaptador(OrdenDelDiaGatewayIntPuerto gateway,
                                      SolicitudGatewayIntPuerto gatewaySolicitud,
                                      ExcepcionesFormateadorIntPuerto formateadorExcepciones,
                                      LogCUIntPuerto log,
                                      OrdenDelDiaExportador exportador){
        this.gateway = gateway;
        this.formateadorExcepciones = formateadorExcepciones;
        this.log = log;
        this.exportador = exportador;
        this.gatewaySolicitud = gatewaySolicitud;
    }

    @Override
    public List<OrdenDelDia> getOrdenesDelDia() {
        List<OrdenDelDia> respuesta = gateway.getOrdenesDelDia();
        if(respuesta.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, ORDENES_DEL_DIA));
        return respuesta;
    }

    @Override
    public List<OrdenDelDia> getOrdenesDelDiaPorEstado(boolean estado) {
        List<OrdenDelDia> respuesta = gateway.getOrdenesDelDiaPorEstado(estado);
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
        String uuidOrdenDelDia = UUID.randomUUID().toString();
        ordenDelDia.setUuidOrdenDelDia(uuidOrdenDelDia);
        ordenDelDia.setEstado(true);
        log.crearLog("Crear Orden del Día", String.format("Orden del día con uuid %s creada: %s", uuidOrdenDelDia, ordenDelDia.getNombre()), token);
        return gateway.guardarOrdenDelDia(ordenDelDia);
    }

    @Override
    public OrdenDelDia actualizarOrdenDelDia(String uuidOrdenDelDia, OrdenDelDia ordenDelDia, String token) {
        OrdenDelDia ordenDelDiaActualizar = getOrdenDelDia(uuidOrdenDelDia);
        ordenDelDiaActualizar.actualizar(ordenDelDia);
        log.crearLog("Actualizar Orden del Día", String.format("Orden del día con uuid %s actualizado: %s", uuidOrdenDelDia, ordenDelDia.getNombre()), token);
        return gateway.guardarOrdenDelDia(ordenDelDiaActualizar);
    }

    @Override
    public PaginacionRespuestaDTO<OrdenDelDia> buscarOrdenDelDiaPorNumeroActa(String nombre, int pagina, int tamanio) {
        PaginacionRespuestaDTO<OrdenDelDia> resultado = gateway.getOrdenesDelDia(nombre, pagina, tamanio);
        if (resultado.getContent().isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format("No existen órdenes del día cuyo nombre coincida con '%s'", nombre));
        return resultado;
    }

    @Override
    public byte[] generarOrdenDelDia(String uuidOrden) {
        OrdenDelDia ordenDelDia = getOrdenDelDia(uuidOrden);
        List<Solicitud> solicitudes = gatewaySolicitud.getSolicitudesPorOrdenDelDia(uuidOrden);
        String fecha = ordenDelDia.getFecha() != null ? ordenDelDia.getFecha() : "";
        String dia = "";
        String mes = "";
        String anio = "";

        if (!fecha.isEmpty() && fecha.contains("-")) {
            String[] partesFecha = fecha.split("-");
            if (partesFecha.length == 3) {
                anio = partesFecha[0] != null ? partesFecha[0] : "";
                mes = partesFecha[1] != null ? partesFecha[1] : "";
                dia = partesFecha[2] != null ? partesFecha[2] : "";
            }
        }

        HashMap<String, String> datosOrden = new HashMap<>();

        datosOrden.put("{{CIUDAD}}", ordenDelDia.getCiudad() != null ? ordenDelDia.getCiudad() : "");
        datosOrden.put("{{DIA}}", dia);
        datosOrden.put("{{MES}}", mes);
        datosOrden.put("{{ANIO}}", anio);
        datosOrden.put("{{HORA_INICIO}}", ordenDelDia.getHoraInicio() != null ? ordenDelDia.getHoraInicio() : "");
        datosOrden.put("{{HORA_FIN}}", ordenDelDia.getHoraFin() != null ? ordenDelDia.getHoraFin() : "");
        datosOrden.put("{{LUGAR}}", ordenDelDia.getLugarReunion() != null ? ordenDelDia.getLugarReunion() : "");
        datosOrden.put("{{ACTA}}", ordenDelDia.getNumeroActa() != null ? ordenDelDia.getNumeroActa() : "");

        datosOrden.put("{{ASUNTOS_DECANO}}", "");
        datosOrden.put("{{ASUNTOS_PREGRADO}}", "");
        datosOrden.put("{{ASUNTOS_POSGRADOS}}", "");
        datosOrden.put("{{ASUNTOS_DELEGADOS_DECANO}}", "");
        datosOrden.put("{{SOLICITUDES_COMISION_ACADEMICA_INTERIOR}}", "");
        datosOrden.put("{{SOLICITUDES_COMISION_ACADEMICA_EXTERIOR}}", "");
        datosOrden.put("{{INFORME_COMISION_ACADEMICA}}", "");
        datosOrden.put("{{ASUNTOS_VARIOS}}", "");

        if (!solicitudes.isEmpty()) {

            Map<String, StringBuilder> secciones = new HashMap<>();
            Map<String, Integer> contadores = new HashMap<>();

            for (Solicitud solicitud : solicitudes) {

                String seccion = solicitud.getObjTipoSolicitud() != null &&
                        solicitud.getObjTipoSolicitud().getSeccion() != null
                        ? solicitud.getObjTipoSolicitud().getSeccion().toLowerCase()
                        : "";

                if (!secciones.containsKey(seccion)) {
                    secciones.put(seccion, new StringBuilder());
                    contadores.put(seccion, 0);
                }

                int subNumero = contadores.get(seccion) + 1;
                contadores.put(seccion, subNumero);

                int numeroSeccion;

                switch (seccion) {
                    case ApplicationConstantes.ASUNTOS_DECANO: numeroSeccion = 1; break;
                    case ApplicationConstantes.ASUNTOS_PREGRADO: numeroSeccion = 2; break;
                    case ApplicationConstantes.ASUNTOS_POSGRADOS: numeroSeccion = 3; break;
                    case ApplicationConstantes.ASUNTOS_DELEGADOS_EN_DECANO: numeroSeccion = 4; break;
                    case ApplicationConstantes.SOLICITUD_COMISION_ACADEMICA_INTERIOR_PAIS: numeroSeccion = 5; break;
                    case ApplicationConstantes.SOLICITUD_COMISION_ACADEMICA_EXTERIOR_PAIS: numeroSeccion = 6; break;
                    case ApplicationConstantes.INFORME_COMISION_ACADEMICA: numeroSeccion = 7; break;
                    case ApplicationConstantes.ASUNTOS_VARIOS: numeroSeccion = 8; break;
                    default: continue;
                }

                StringBuilder contenido = secciones.get(seccion);

                contenido.append(numeroSeccion)
                        .append(".")
                        .append(subNumero)
                        .append(" ")
                        .append(solicitud.getNombre() != null ? solicitud.getNombre() : "")
                        .append("\n")
                        .append(solicitud.getDescripcion() != null ? solicitud.getDescripcion() : "")
                        .append("\n\n");
            }

            for (Map.Entry<String, StringBuilder> entry : secciones.entrySet()) {
                String seccion = entry.getKey();
                String texto = entry.getValue().toString().trim();

                switch (seccion) {
                    case ApplicationConstantes.ASUNTOS_DECANO:
                        datosOrden.put("{{ASUNTOS_DECANO}}", texto);
                        break;
                    case ApplicationConstantes.ASUNTOS_PREGRADO:
                        datosOrden.put("{{ASUNTOS_PREGRADO}}", texto);
                        break;
                    case ApplicationConstantes.ASUNTOS_POSGRADOS:
                        datosOrden.put("{{ASUNTOS_POSGRADOS}}", texto);
                        break;
                    case ApplicationConstantes.ASUNTOS_DELEGADOS_EN_DECANO:
                        datosOrden.put("{{ASUNTOS_DELEGADOS_DECANO}}", texto);
                        break;
                    case ApplicationConstantes.SOLICITUD_COMISION_ACADEMICA_INTERIOR_PAIS:
                        datosOrden.put("{{SOLICITUDES_COMISION_ACADEMICA_INTERIOR}}", texto);
                        break;
                    case ApplicationConstantes.SOLICITUD_COMISION_ACADEMICA_EXTERIOR_PAIS:
                        datosOrden.put("{{SOLICITUDES_COMISION_ACADEMICA_EXTERIOR}}", texto);
                        break;
                    case ApplicationConstantes.INFORME_COMISION_ACADEMICA:
                        datosOrden.put("{{INFORME_COMISION_ACADEMICA}}", texto);
                        break;
                    case ApplicationConstantes.ASUNTOS_VARIOS:
                        datosOrden.put("{{ASUNTOS_VARIOS}}", texto);
                        break;
                }
            }
        }

        return exportador.exportarOrdenDelDia(datosOrden);
    }
}
