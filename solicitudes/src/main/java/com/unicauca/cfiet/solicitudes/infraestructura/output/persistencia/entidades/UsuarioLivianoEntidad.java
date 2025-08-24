package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "usuariosLivianos")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class UsuarioLivianoEntidad {
    @Id
    @Column(length = 100)
    private String uuidUsuario;
    @Column(nullable = false, length = 200)
    private String nombres;
    @Column(nullable = false, length = 200)
    private String apellidos;
    @Column(nullable = false)
    private Boolean estado;
}
