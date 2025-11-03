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
    private String uuidFuncionario;

    public TipoSolicitud(){
        this.anexos = new ArrayList<>();
    }

    public boolean revisarSeccion() {
        String seccion = getSeccion();
        switch (seccion.trim().toLowerCase()) {
            case "decanatura":
                setSeccion("Decanatura");
                return true;
            case "posgrado":
                setSeccion("Posgrado");
                return true;
            case "pregrado":
                setSeccion("Pregrado");
                return true;
            case "otro":
                setSeccion("Otro");
                return true;
            default:
                return false;
        }
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
        if(tipoSolicitud.getUuidFuncionario() != null && !tipoSolicitud.getUuidFuncionario().isBlank())
            this.uuidFuncionario = tipoSolicitud.getUuidFuncionario();
    }

}
