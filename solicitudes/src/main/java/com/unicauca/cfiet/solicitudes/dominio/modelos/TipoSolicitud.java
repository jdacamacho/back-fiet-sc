package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class TipoSolicitud {
    private String uuidTipoSolicitud;
    private String nombre;
    private String descripcion;
    private String seccion;
    private List<TipoAnexo> anexos;
    private Funcionario objFuncionarioEncargado;

    public TipoSolicitud(){
        this.anexos = new ArrayList<>();
    }

    public boolean revisarAnexos(){
        for(TipoAnexo anexo: anexos){
            if(!anexo.formatoEsValido())
                return false;
        }
        return true;
    }

    public void actualizarTipoSolicitud(TipoSolicitud tipoSolicitud) {
        if (tipoSolicitud.getNombre() != null && !tipoSolicitud.getNombre().isBlank())
            this.nombre = tipoSolicitud.getNombre();
        if (tipoSolicitud.getDescripcion() != null && !tipoSolicitud.getDescripcion().isBlank())
            this.descripcion = tipoSolicitud.getDescripcion();
        if (tipoSolicitud.getSeccion() != null && !tipoSolicitud.getSeccion().isBlank())
            this.seccion = tipoSolicitud.getSeccion();
        if (tipoSolicitud.getAnexos() != null && !tipoSolicitud.getAnexos().isEmpty()) {
            for(TipoAnexo anexo : tipoSolicitud.getAnexos()) {
                anexo.setUuidTipoAnexo(UUID.randomUUID().toString());
                anexo.setObjTipoSolicitud(this);
            }
            this.anexos = tipoSolicitud.getAnexos();
        }
        if (tipoSolicitud.getObjFuncionarioEncargado() != null)
            this.objFuncionarioEncargado = tipoSolicitud.getObjFuncionarioEncargado();

    }

}
