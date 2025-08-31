package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "logs")
@Getter
@Setter
public class LogEntidad {
    @Id
    @Column(length = 100)
    private String uuidLog;
    @Column(nullable = false, length = 200)
    private String accion;
    @Column(nullable = false, length = 45)
    private String fecha;
    @Column(nullable = false, length = 200)
    private String resultado;
    @ManyToOne
    @JoinColumn(name = "uuidUsuario", nullable = false)
    private UsuarioEntidad objUsuarioLog;
}
