package com.unicauca.cfiet.solicitudes.dominio.modelos;

import com.unicauca.cfiet.solicitudes.dominio.helper.constantes.ApplicationConstantes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@AllArgsConstructor
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
    private List<Log> logs;

    public Usuario(){
        super();
        this.roles = new ArrayList<>();
        this.logs = new ArrayList<>();
    }

    public boolean revisarTipoDocumento() {
        String tipoDocumento = getTipoDocumento();
        switch (tipoDocumento.trim().toLowerCase()) {
            case ApplicationConstantes.CEDULA_CIUDADANIA_LOWER:
                setTipoDocumento(ApplicationConstantes.CEDULA_CIUDADANIA);
                return true;
            case ApplicationConstantes.TARJETA_IDENTIDAD_LOWER:
                setTipoDocumento(ApplicationConstantes.TARJETA_IDENTIDAD);
                return true;
            case ApplicationConstantes.CEDULA_EXTRANJERIA_LOWER:
                setTipoDocumento(ApplicationConstantes.CEDULA_EXTRANJERIA);
                return true;
            default:
                return false;
        }
    }

    public boolean rolesSonValidos(List<Rol> rolesValidos) {
        List<Rol> roles = this.getRoles();
        int wasFound = 0;
        for (Rol rol : roles) {
            for (Rol rolValido : rolesValidos) {
                if (rol.getUuidRol().equals(rolValido.getUuidRol()))
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
            case ApplicationConstantes.FUNCIONARIO:
                instancia = new Funcionario();
                break;
            default:
                instancia = new Usuario();
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
