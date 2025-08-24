package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@SuperBuilder
@Getter
@Setter
public class Decano extends Usuario{
    public Decano(){
        super();
    }
}
