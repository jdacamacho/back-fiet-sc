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
public class OrdenDelDiaDTOPeticion {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 5, max = 200, message = "El nombre debe tener entre 5 y 200 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción debe tener entre 0 y 500 caracteres")
    private String descripcion;

    @Size(max = 200, message = "La ciudad debe tener entre 0 y 200 caracteres")
    private String ciudad;

    @Size(max = 200, message = "La fecha debe tener entre 0 y 200 caracteres")
    private String fecha;

    @Size(max = 200, message = "La hora de inicio debe tener entre 0 y 200 caracteres")
    private String horaInicio;

    @Size(max = 200, message = "La hora de fin debe tener entre 0 y 200 caracteres")
    private String horaFin;

    @Size(max = 200, message = "El lugar de reunión debe tener entre 0 y 200 caracteres")
    private String lugarReunion;

    @NotNull(message = "El número de acta no puede ser nulo")
    @NotBlank(message = "El número de acta no puede estar vacío")
    @Size(min = 5, max = 200, message = "El número de acta debe tener entre 5 y 200 caracteres")
    private String numeroActa;

    @NotNull(message = "El estado es obligatorio")
    private boolean estado;
}
