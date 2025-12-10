package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.*;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {
    private String uuidLog;
    private String accion;
    private String fecha;
    private String resultado;
    private Usuario objUsuarioLog;
}
