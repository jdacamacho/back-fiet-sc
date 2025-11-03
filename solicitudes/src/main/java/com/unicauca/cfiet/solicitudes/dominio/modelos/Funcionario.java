package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class Funcionario extends Usuario{
    private List<TipoSolicitud> tiposSolicitudes;

    public Funcionario(){
        super();
        this.tiposSolicitudes = new ArrayList<>();
    }
}
