package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.DTOPeticion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@Builder
public class TipoAnexoDTOPeticion {
    @NotNull(message = "El nombre del anexo no puede ser nulo")
    @NotBlank(message = "El nombre del anexo no puede estar vacío")
    @Size(min = 3, max = 45, message = "El nombre del anexo debe tener entre 3 y 45 caracteres")
    private String nombre;

    @Size(max = 200, message = "La descripción del anexo no puede exceder los 200 caracteres")
    private String descripcion;

    @NotNull(message = "El formato del anexo no puede ser nulo")
    @NotBlank(message = "El formato del anexo no puede estar vacío")
    @Size(max = 45, message = "El formato del anexo no puede exceder los 45 caracteres")
    private String formato;

    @NotNull(message = "La obligatoriedad del anexo debe ser especificada")
    private Boolean obligatoriedad;
}
