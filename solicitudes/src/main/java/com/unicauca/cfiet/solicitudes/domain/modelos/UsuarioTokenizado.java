package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.*;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTokenizado{
    private String uuidUsuario;
    private String token;
}
