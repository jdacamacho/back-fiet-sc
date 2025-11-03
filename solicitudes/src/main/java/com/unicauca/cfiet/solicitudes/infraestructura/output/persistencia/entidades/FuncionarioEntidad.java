package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "funcionarios")
@Getter
@Setter
public class FuncionarioEntidad extends UsuarioEntidad{
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objFuncionarioEncargado")
    private List<TipoSolicitudEntidad> tiposSolicitudes;

    public FuncionarioEntidad(){
        super();
        this.tiposSolicitudes = new ArrayList<>();
    }
}
