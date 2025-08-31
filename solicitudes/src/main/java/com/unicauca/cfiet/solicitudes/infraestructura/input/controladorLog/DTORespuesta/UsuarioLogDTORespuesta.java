package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorLog.DTORespuesta;

import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.RolUsuarioDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.TipoUsuarioDTORespuesta;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class UsuarioLogDTORespuesta extends UsuarioLogLivianoDTORespuesta {
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String correoElectronico;
    private String username;
    private TipoUsuarioDTORespuesta objTipoUsuario;
    private List<RolUsuarioDTORespuesta> roles;

    public UsuarioLogDTORespuesta(){
        super();
        this.roles = new ArrayList<>();
    }
}
