package com.unicauca.cfiet.solicitudes.dominio.helper;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class PaginacionRespuestaDTO <T>{
    private List<T> content;
    private long totalElements;

    public PaginacionRespuestaDTO(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}
