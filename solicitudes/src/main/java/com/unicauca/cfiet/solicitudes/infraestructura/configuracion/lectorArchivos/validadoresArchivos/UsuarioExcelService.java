package com.unicauca.cfiet.solicitudes.infraestructura.configuracion.lectorArchivos.validadoresArchivos;

import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioDTOPeticion;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
@RequiredArgsConstructor
public class UsuarioExcelService {
    private final Validator validator;

    public Map<String, String> validarUsuario(UsuarioDTOPeticion peticion) {
        Set<ConstraintViolation<UsuarioDTOPeticion>> violaciones = validator.validate(peticion);
        if (violaciones.isEmpty())
            return null;

        return violaciones.stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));
    }
}
