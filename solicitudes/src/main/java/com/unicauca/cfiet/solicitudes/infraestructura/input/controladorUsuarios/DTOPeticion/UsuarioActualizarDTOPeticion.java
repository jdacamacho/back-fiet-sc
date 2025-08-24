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
public class UsuarioActualizarDTOPeticion extends UsuarioLivianoDTOPeticion{
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String correoElectronico;
    private Boolean estado;
    private String username;
    private TipoUsuarioDTOPeticion objTipoUsuario;
    private List<RolUsuarioDTOPeticion> roles;

    public UsuarioActualizarDTOPeticion(){
        super();
        this.roles = new ArrayList<>();
    }
}
