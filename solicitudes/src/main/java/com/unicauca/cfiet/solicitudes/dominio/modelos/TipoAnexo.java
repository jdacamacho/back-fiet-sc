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
public class TipoAnexo {
    private String uuidTipoAnexo;
    private String nombre;
    private String descripcion;
    private String formato;
    private Boolean obligatoriedad;
    private TipoSolicitud objTipoSolicitud;

    public boolean formatoEsValido(){
        if(!formato.toUpperCase().equals("PDF") && !formato.toUpperCase().equals("DOCX"))
            return false;
        return true;
    }
}
