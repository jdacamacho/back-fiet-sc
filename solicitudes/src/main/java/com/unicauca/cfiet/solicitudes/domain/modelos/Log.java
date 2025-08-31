package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Log {
    private String uuidLog;
    private String accion;
    private String fecha;
    private String resultado;
    private Usuario objUsuarioLog;

    public Log(){}
}
