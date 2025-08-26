package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuarioEntidad extends UsuarioLivianoEntidad{
    @Column(nullable = false, length = 45)
    private String tipoDocumento;
    @Column(nullable = false, unique = true, length = 45)
    private String numeroDocumento;
    @Column(nullable = false, length = 45)
    private String telefono;
    @Column(nullable = false, unique = true, length = 45)
    private String correoElectronico;
    @Column(nullable = false, unique = true, length = 100)
    private String username;
    @Column(nullable = false, length = 300)
    private String password;
    @ManyToOne
    @JoinColumn(name = "uuidTipoUsuario", nullable = false)
    private TipoUsuarioEntidad objTipoUsuario;
    @ManyToMany
    @JoinTable(name="Usuario_has_Roles",
            joinColumns = @JoinColumn(name = "uuidUsuario"),
            inverseJoinColumns = @JoinColumn(name = "uuidRol")
    )
    private List<RolEntidad> roles;

    public UsuarioEntidad(){
        super();
        this.roles = new ArrayList<>();
    }
}
