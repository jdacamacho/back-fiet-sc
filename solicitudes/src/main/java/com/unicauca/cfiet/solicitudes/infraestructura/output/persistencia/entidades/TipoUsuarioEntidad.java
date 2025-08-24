package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "tiposUsuario")
@Data
@NoArgsConstructor
public class TipoUsuarioEntidad {
    @Id
    @Column(length = 100)
    private String uuidTipoUsuario;
    @Column(nullable = false, unique = true, length = 45)
    private String nombre;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "objTipoUsuario")
    private List<UsuarioEntidad> usuarios;
}
