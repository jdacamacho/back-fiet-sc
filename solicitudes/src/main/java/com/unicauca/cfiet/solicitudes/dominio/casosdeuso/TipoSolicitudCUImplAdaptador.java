package com.unicauca.cfiet.solicitudes.dominio.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.LogCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.input.TipoSolicitudCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.TipoSolicitudGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.UsuarioGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.dominio.modelos.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones.MensajesError;

import java.util.List;
import java.util.UUID;

/**
 * Implementación de la interfaz de los casos de uso para la gestión de Tipos de Solicitud.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class TipoSolicitudCUImplAdaptador implements TipoSolicitudCUIntPuerto {
    private final TipoSolicitudGatewayIntPuerto gateway;
    private final UsuarioGatewayIntPuerto gatewayUsuario;
    private final ExcepcionesFormateadorIntPuerto formateadorExcepciones;
    private final LogCUIntPuerto log;
    /* Constantes */
    private static final String TIPOS_SOLICITUD = "tipos de solicitud";
    private static final String TIPO_SOLICITUD = "tipo de solicitud";
    private static final String USUARIO = "usuario";

    public TipoSolicitudCUImplAdaptador(TipoSolicitudGatewayIntPuerto gateway,
                                        UsuarioGatewayIntPuerto gatewayUsuario,
                                        ExcepcionesFormateadorIntPuerto formateadorExcepciones,
                                        LogCUIntPuerto log){
        this.gateway = gateway;
        this.gatewayUsuario = gatewayUsuario;
        this.formateadorExcepciones = formateadorExcepciones;
        this.log = log;
    }

    @Override
    public List<TipoSolicitud> getTiposSolicitud() {
        List<TipoSolicitud> tipoSolicitudes = gateway.getTiposSolicitudes();
        if(tipoSolicitudes.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, TIPOS_SOLICITUD));
        return  tipoSolicitudes;
    }

    @Override
    public List<TipoSolicitud> getTiposSolicitud(int pagina, int tamanio) {
        if(pagina < 0 || tamanio < 0)
            formateadorExcepciones.lanzarMalFormato(MensajesError.PAGINACION_ERROR);
        List<TipoSolicitud> tipoSolicitudes = gateway.getTiposSolicitudes(pagina, tamanio);
        if(tipoSolicitudes.isEmpty())
            formateadorExcepciones.lanzarSinInformacion(String.format(MensajesError.SIN_REGISTROS, TIPOS_SOLICITUD));
        return  tipoSolicitudes;
    }

    @Override
    public TipoSolicitud getTipoSolicitud(String uuidTipoSolicitud) {
        TipoSolicitud tipoSolicitud = gateway.getTipoSolicitud(uuidTipoSolicitud);
        if(tipoSolicitud == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, TIPOS_SOLICITUD, uuidTipoSolicitud));
        return tipoSolicitud;
    }

    @Override
    public TipoSolicitud crearTipoSolicitud(TipoSolicitud tipoSolicitud, String uuidFuncionario, String token) {
        asignarFuncionario(tipoSolicitud, uuidFuncionario);
        tipoSolicitud.setUuidTipoSolicitud(UUID.randomUUID().toString());
        if(tipoSolicitud.getAnexos() != null){
            if(!tipoSolicitud.revisarAnexos())
                formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.MAL_FORMATO_ANEXO);

            for(TipoAnexo anexo : tipoSolicitud.getAnexos()) {
                anexo.setUuidTipoAnexo(UUID.randomUUID().toString());
                anexo.setObjTipoSolicitud(tipoSolicitud);
            }
        }
        log.crearLog("Crear Tipo de Solicitud", String.format("Tipo de Solicitud %s con uuid %s",tipoSolicitud.getNombre(), tipoSolicitud.getUuidTipoSolicitud()),token);
        return gateway.guardarTipoSolicitud(tipoSolicitud);
    }

    @Override
    public TipoSolicitud actualizarTipoSolicitud(String uuidTipoSolicitud, String uuidFuncionario, TipoSolicitud tipoSolicitud, String token) {
        TipoSolicitud tipoSolicitudObtenida = gateway.getTipoSolicitud(uuidTipoSolicitud);

        if(tipoSolicitudObtenida == null)
            formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, TIPO_SOLICITUD, uuidTipoSolicitud));
        if(tipoSolicitudObtenida.getObjFuncionarioEncargado() != null && (uuidFuncionario == null ||  uuidFuncionario.isBlank())){
            Funcionario funcionarioAnterior = tipoSolicitudObtenida.getObjFuncionarioEncargado();
            funcionarioAnterior.getTiposSolicitudes().remove(tipoSolicitudObtenida);
            tipoSolicitudObtenida.setObjFuncionarioEncargado(null);
        }
        if(tipoSolicitudObtenida.getObjFuncionarioEncargado() == null && uuidFuncionario != null && !uuidFuncionario.isBlank())
            asignarFuncionario(tipoSolicitud, uuidFuncionario);
        if(tipoSolicitudObtenida.getObjFuncionarioEncargado() != null && !tipoSolicitudObtenida.getObjFuncionarioEncargado().getUuidUsuario().equals(uuidFuncionario)
                && uuidFuncionario != null && !uuidFuncionario.isBlank())
            asignarFuncionario(tipoSolicitud, uuidFuncionario);

        tipoSolicitudObtenida.actualizarTipoSolicitud(tipoSolicitud);
        if(!tipoSolicitud.revisarAnexos())
            formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.MAL_FORMATO_ANEXO);
        log.crearLog("Actualizar Tipo Solicitud", "Tipo de Solicitud Actualizado con exito!", token);
        return gateway.guardarTipoSolicitud(tipoSolicitudObtenida);
    }

    private void asignarFuncionario(TipoSolicitud tipoSolicitud, String uuidFuncionario){
        if(uuidFuncionario != null && !uuidFuncionario.isBlank()) {
            Usuario usuario = gatewayUsuario.getUsuario(uuidFuncionario);
            if (usuario == null)
                formateadorExcepciones.lanzarEntidadNoExiste(String.format(MensajesError.ENTIDAD_NO_ENCONTRADA, USUARIO, uuidFuncionario));
            if(usuario instanceof  Funcionario) {
                Funcionario funcionario = (Funcionario) usuario;
                tipoSolicitud.setObjFuncionarioEncargado(funcionario);
                funcionario.getTiposSolicitudes().add(tipoSolicitud);
            } else
                formateadorExcepciones.lanzarReglaNegocioViolada(MensajesError.MAL_ASIGNACION);
        }
    }
}
