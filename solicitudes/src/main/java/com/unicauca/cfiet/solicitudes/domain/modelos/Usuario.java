package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@SuperBuilder
public class Usuario extends UsuarioLiviano{
    private String tipoDocumento;
    private String numeroDocumento;
    private String telefono;
    private String correoElectronico;
    private String username;
    private String password;
    private TipoUsuario objTipoUsuario;
    private List<Rol> roles;

    public Usuario(){
        super();
        this.roles = new ArrayList<>();
    }

    public Usuario crearInstancia(String tipoUsuario){
        Usuario instancia;
        switch (tipoUsuario.toUpperCase()) {
                case "DECANO":
                instancia = new Decano();
                break;
            case "SECRETARIOGENERAL":
                instancia = new SecretarioGeneral();
                break;
            case "FUNCIONARIO":
                instancia = new Funcionario();
                break;
            default:
                return null;
        }

        instancia.setUuidUsuario(this.getUuidUsuario());
        instancia.setNombres(this.getNombres());
        instancia.setApellidos(this.getApellidos());
        instancia.setTipoDocumento(this.getTipoDocumento());
        instancia.setNumeroDocumento(this.getNumeroDocumento());
        instancia.setTelefono(this.getTelefono());
        instancia.setCorreoElectronico(this.getCorreoElectronico());
        instancia.setEstado(this.getEstado());
        instancia.setUsername(this.getUsername());
        instancia.setPassword(this.getPassword());
        instancia.setObjTipoUsuario(this.getObjTipoUsuario());
        instancia.setRoles(new ArrayList<>(this.getRoles()));
        return instancia;
    }

    public void actualizarUsuario(Usuario usuario) {
        if (usuario.getTipoDocumento() != null && !usuario.getTipoDocumento().isBlank())
            this.setTipoDocumento(usuario.getTipoDocumento());
        if (usuario.getNumeroDocumento() != null && !usuario.getNumeroDocumento().isBlank())
            this.setNumeroDocumento(usuario.getNumeroDocumento());
        if (usuario.getTelefono() != null && !usuario.getTelefono().isBlank())
            this.setTelefono(usuario.getTelefono());
        if (usuario.getCorreoElectronico() != null && !usuario.getCorreoElectronico().isBlank())
            this.setCorreoElectronico(usuario.getCorreoElectronico());
        if (usuario.getEstado() != null)
            this.setEstado(usuario.getEstado());
        if (usuario.getUsername() != null && !usuario.getUsername().isBlank())
            this.setUsername(usuario.getUsername());
        if (usuario.getObjTipoUsuario() != null)
            this.setObjTipoUsuario(usuario.getObjTipoUsuario());
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty())
            this.setRoles(usuario.getRoles());
        if (usuario.getNombres() != null && !usuario.getNombres().isBlank())
            this.setNombres(usuario.getNombres());
        if (usuario.getApellidos() != null && !usuario.getApellidos().isBlank())
            this.setApellidos(usuario.getApellidos());
    }
}
