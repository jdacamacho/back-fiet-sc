package com.unicauca.cfiet.solicitudes.dominio.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.LogCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.input.SolicitudCUintPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.*;
import com.unicauca.cfiet.solicitudes.dominio.helper.PaginacionRespuestaDTO;
import com.unicauca.cfiet.solicitudes.dominio.modelos.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones.MensajesError;
import java.util.List;
import java.util.UUID;

/**
 * Implementación de la interfaz de los casos de uso para la gestión de solicitudes.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class SolicitudCUImplAdaptador implements SolicitudCUintPuerto {
    private final SolicitudGatewayIntPuerto gateway;
    private final TipoSolicitudGatewayIntPuerto gatewayTipoSolicitud;
    private final OrdenDelDiaGatewayIntPuerto gatewayOrdenDelDia;
    private final UsuarioGatewayIntPuerto gatewayUsuario;
    private final ExcepcionesFormateadorIntPuerto formateadorExcepciones;
    private final LogCUIntPuerto log;
    /* Constantes */
    private static final String SOLICITUDES = "Solicitudes";
    private static final String SOLICITUD = "Solicitud";
    private static final String ORDEN_DEL_DIA = "Orden del Día";
    private static final String TIPOS_SOLICITUD = "tipos de solicitud";
    private static final String USUARIO = "Usuario";

    public SolicitudCUImplAdaptador(SolicitudGatewayIntPuerto gateway,
                                    ExcepcionesFormateadorIntPuerto formateadorExcepciones,
                                    TipoSolicitudGatewayIntPuerto gatewayTipoSolicitud,
                                    OrdenDelDiaGatewayIntPuerto gatewayOrdenDelDia,
                                    UsuarioGatewayIntPuerto gatewayUsuario,
                                    LogCUIntPuerto log){
        this.gateway = gateway;
        this.gatewayTipoSolicitud = gatewayTipoSolicitud;
        this.gatewayOrdenDelDia = gatewayOrdenDelDia;
        this.formateadorExcepciones = formateadorExcepciones;
        this.gatewayUsuario = gatewayUsuario;
        this.log = log;
    }

    @Override
    public List<Solicitud> getSolicitudes() {
        List<Solicitud> respuesta = gateway.getSolicitudes();
        if(respuesta.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, SOLICITUDES));
        return respuesta;
    }

    @Override
    public PaginacionRespuestaDTO<Solicitud> getSolicitudes(int pagina, int tamanio) {
        if (pagina < 0 || tamanio < 0)
            formateadorExcepciones.lanzarMalFormato(MensajesError.PAGINACION_ERROR);
        PaginacionRespuestaDTO<Solicitud> respuesta = gateway.getSolicitudes(pagina, tamanio);
        if (respuesta.getContent().isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, SOLICITUDES));
        return respuesta;
    }

    @Override
    public Solicitud getSolicitud(String uuidSolicitud) {
        Solicitud solicitud = gateway.getSolicitud(uuidSolicitud);
        if(solicitud == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, SOLICITUD, uuidSolicitud));
        return  solicitud;
    }

    @Override
    public Solicitud crearSolicitud(Solicitud solicitud) {
        checkSolicitud(solicitud);
        String uuidSolicitud = UUID.randomUUID().toString();
        solicitud.setUuidSolicitud(uuidSolicitud);
        solicitud.setEstado("PENDIENTE AL ORDEN DEL DÍA");
        solicitud.getInformacionSolicitante().setUuidInformacionSolicitante(UUID.randomUUID().toString());
        solicitud.getInformacionSolicitante().setSolicitud(solicitud);

        for(Anexo currentAnexo : solicitud.getAnexos()){
            currentAnexo.setUuidAnexo(UUID.randomUUID().toString());
            currentAnexo.setObjSolicitud(solicitud);
        }

        return gateway.guardarSolicitud(solicitud);
    }

    @Override
    public Solicitud actualizarSolicitud(String uuidSolicitud, Solicitud solicitud, String token) {
        Solicitud solicitudOriginal = gateway.getSolicitud(uuidSolicitud);
        if(!solicitud.getUuidFuncionario().equals(solicitudOriginal.getObjFuncionario().getUuidUsuario())){
            Usuario usuario = gatewayUsuario.getUsuario(solicitud.getUuidFuncionario());
            if(usuario == null)
                formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, USUARIO, solicitud.getUuidFuncionario()));

            if (!(usuario instanceof Funcionario))
                formateadorExcepciones.lanzarMalFormato(String.format(MensajesError.TIPO_DE_USUARIO_NO_VALIDO));
            Funcionario funcionarioNuevo = (Funcionario) usuario;
            solicitudOriginal.setObjFuncionario(funcionarioNuevo);
        }

        if (!solicitud.getUuidOrdenDelDia().equals(solicitudOriginal.getObjOrdenDelDia().getUuidOrdenDelDia())) {
            OrdenDelDia ordenNuevo = gatewayOrdenDelDia.getOrdenDelDia(solicitud.getUuidOrdenDelDia());
            if (ordenNuevo == null)
                formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, ORDEN_DEL_DIA, solicitud.getUuidOrdenDelDia()));

            solicitudOriginal.setObjOrdenDelDia(ordenNuevo);
        }

        solicitudOriginal.actualizar(solicitud);
        log.crearLog("Solicitud modificada", String.format("Se modifico la información de la solicitud %s" , solicitudOriginal.getNombre()), token);
        return gateway.guardarSolicitud(solicitudOriginal);
    }

    private void checkSolicitud(Solicitud solicitud){
        TipoSolicitud tipoSolicitud = gatewayTipoSolicitud.getTipoSolicitud(solicitud.getUuidTipoSolicitud());
        if(tipoSolicitud == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, TIPOS_SOLICITUD, solicitud.getUuidTipoSolicitud()));

        solicitud.setObjTipoSolicitud(tipoSolicitud);

        OrdenDelDia ordenDelDia = gatewayOrdenDelDia.getOrdenDelDia(solicitud.getUuidOrdenDelDia());
        if(ordenDelDia == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, ORDEN_DEL_DIA, solicitud.getUuidOrdenDelDia()));

        solicitud.setObjOrdenDelDia(ordenDelDia);

        Funcionario funcionario = tipoSolicitud.getObjFuncionarioEncargado();
        solicitud.setObjFuncionario(funcionario);;
    }
}
