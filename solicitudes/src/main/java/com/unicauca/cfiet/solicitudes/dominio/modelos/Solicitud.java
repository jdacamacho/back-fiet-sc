package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class Solicitud {
    private String uuidSolicitud;
    private String consecutivo;
    private String nombre;
    private String descripcion;
    private String estado;
    private TipoSolicitud objTipoSolicitud;
    private String uuidTipoSolicitud;
    private List<Anexo> anexos;
    private OrdenDelDia objOrdenDelDia;
    private String uuidOrdenDelDia;
    private InformacionSolicitante informacionSolicitante;
    private Funcionario objFuncionario;
    private String uuidFuncionario;

    public void actualizar(Solicitud other){
        if (other == null) return;
        this.consecutivo = other.consecutivo;
        this.nombre = other.nombre;
        this.descripcion = other.descripcion;
        this.estado = other.estado;
    }
}
