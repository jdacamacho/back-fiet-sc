package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class SolicitudPublicaDTOPeticion {
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 5, max = 200, message = "El nombre debe tener entre 5 y 200 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción debe tener maximo 500 caracteres")
    private String descripcion;

    @NotNull(message = "El tipo de solicitud es obligatorio")
    @NotBlank(message = "El tipo de solicitud no puede estar vacío")
    @Size(min = 5, max = 200, message = "El tipo de solicitud debe tener entre 5 y 200 caracteres")
    private String uuidTipoSolicitud;

    private List<AnexoDTOPeticion> anexos;

    @NotNull(message = "La información del solicitante es obligatoria")
    @Valid
    private InformacionSolicitanteDTOPeticion informacionSolicitante;
}
