package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class AnexoDTOPeticion {
    private String nombre;
    private MultipartFile anexoFile;
}
