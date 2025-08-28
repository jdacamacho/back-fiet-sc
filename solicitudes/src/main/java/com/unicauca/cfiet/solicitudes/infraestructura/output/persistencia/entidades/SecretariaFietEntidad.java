package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "secretariasDecanaturaFiet")
@Getter
@Setter
public class SecretariaFietEntidad extends UsuarioEntidad{
    public SecretariaFietEntidad(){
        super();
    }
}

