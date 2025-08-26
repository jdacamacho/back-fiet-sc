package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class UsuarioLivianoDTOPeticion {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 200, message = "El nombre debe tener entre 2 y 200 caracteres")
    private String nombres;

    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 200, message = "El apellido debe tener entre 2 y 200 caracteres")
    private String apellidos;

    @NotNull(message = "El estado no puede ser nulo")
    private Boolean estado;
}