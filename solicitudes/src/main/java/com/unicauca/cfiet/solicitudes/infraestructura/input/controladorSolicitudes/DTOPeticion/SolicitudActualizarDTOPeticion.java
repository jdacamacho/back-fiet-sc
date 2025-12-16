package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion;

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
public class SolicitudActualizarDTOPeticion {
    @Size(min = 5, max = 200, message = "El consecutivo debe tener entre 5 y 200 caracteres")
    private String consecutivo;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 5, max = 200, message = "El nombre debe tener entre 5 y 200 caracteres")
    private String nombre;

    @Size(min = 5, max = 500, message = "La descripción debe tener entre 5 y 500 caracteres")
    private String descripcion;

    @NotNull(message = "El estado no puede ser nulo")
    @NotBlank(message = "El estado no puede estar vacío")
    @Size(min = 5, max = 200, message = "El estado debe tener entre 5 y 200 caracteres")
    private String estado;

    @NotNull(message = "El funcionario es obligatorio")
    @NotBlank(message = "El funcionario no puede estar vacío")
    @Size(min = 5, max = 200, message = "El funcionario debe tener entre 5 y 200 caracteres")
    private String uuidFuncionario;

    @NotNull(message = "La orden del día es obligatoria")
    @NotBlank(message = "La orden del día no puede estar vacía")
    @Size(min = 5, max = 200, message = "La orden del día debe tener entre 5 y 200 caracteres")
    private String uuidOrdenDelDia;
}
