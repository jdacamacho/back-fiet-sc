package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class UsuarioDTOPeticion extends UsuarioLivianoDTOPeticion{
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String correoElectronico;
    private String username;
    private String password;
    private TipoUsuarioDTOPeticion objTipoUsuario;
    private List<RolUsuarioDTOPeticion> roles;

    public UsuarioDTOPeticion(){
        super();
        this.roles = new ArrayList<>();
    }
}
