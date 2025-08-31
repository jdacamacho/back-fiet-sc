package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
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

    public UsuarioTokenizado tokenizarObjecto(String token) {
        UsuarioTokenizado usuarioTokenizado = new UsuarioTokenizado();
        usuarioTokenizado.setUuidUsuario(this.getUuidUsuario());
        usuarioTokenizado.setEstado(this.getEstado());
        usuarioTokenizado.setNombres(this.getNombres());
        usuarioTokenizado.setApellidos(this.getApellidos());
        usuarioTokenizado.setTipoDocumento(this.tipoDocumento);
        usuarioTokenizado.setNumeroDocumento(this.numeroDocumento);
        usuarioTokenizado.setTelefono(this.telefono);
        usuarioTokenizado.setCorreoElectronico(this.correoElectronico);
        usuarioTokenizado.setUsername(this.username);
        usuarioTokenizado.setObjTipoUsuario(this.objTipoUsuario);
        usuarioTokenizado.setRoles(this.roles);
        usuarioTokenizado.setToken(token);
        return usuarioTokenizado;
    }

    public boolean rolesSonValidos(List<Rol> rolesValidos) {
        List<Rol> roles = this.getRoles();
        int wasFound = 0;
        for (Rol rol : roles) {
            for (Rol rolValido : rolesValidos) {
                if (rol.equals(rolValido))
                    wasFound++;
            }
        }
        return roles.size() == wasFound;
    }

    public boolean tieneRolesDuplicados(){
        Set<Rol> roleSet = new HashSet<>(roles);
        return roleSet.size() < roles.size();
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
            case "SECRETARIADECANATURAFIET":
                instancia = new SecretariaFiet();
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
        if (usuario.getNombres() != null && !usuario.getNombres().isBlank())
            this.setNombres(usuario.getNombres());
        if (usuario.getApellidos() != null && !usuario.getApellidos().isBlank())
            this.setApellidos(usuario.getApellidos());
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty())
            this.setRoles(usuario.getRoles());
        else
            this.setRoles(new ArrayList<>());
    }
}
