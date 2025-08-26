package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class UsuarioDTOPeticion extends UsuarioLivianoDTOPeticion{
    @NotNull(message = "El tipo de documento no puede ser nulo")
    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Size(min = 2, max = 20, message = "El tipo de documento debe tener entre 2 y 20 caracteres")
    private String tipoDocumento;

    @NotNull(message = "El número de documento no puede ser nulo")
    @NotBlank(message = "El número de documento no puede estar vacío")
    @Size(min = 5, max = 20, message = "El número de documento debe tener entre 5 y 20 caracteres")
    private String numeroDocumento;

    @NotNull(message = "El teléfono no puede ser nulo")
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
    @Pattern(regexp = "^\\+\\d+$", message = "El teléfono debe comenzar con '+' seguido de números")
    private String telefono;

    @NotNull(message = "El correo electrónico no puede ser nulo")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(min = 5, max = 100, message = "El correo electrónico debe tener entre 5 y 100 caracteres")
    private String correoElectronico;


    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    private String username;

    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String password;

    @Valid
    @NotNull(message = "El tipo de usuario no puede ser nulo")
    private TipoUsuarioDTOPeticion objTipoUsuario;

    @Valid
    @NotNull(message = "La lista de roles no puede ser nula")
    @Size(min = 1, message = "Debe asignar al menos un rol")
    private List<RolUsuarioDTOPeticion> roles;


    public UsuarioDTOPeticion(){
        super();
        this.roles = new ArrayList<>();
    }
}
