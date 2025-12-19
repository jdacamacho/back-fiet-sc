package com.unicauca.cfiet.solicitudes.dominio.modelos;

import com.unicauca.cfiet.solicitudes.dominio.helper.constantes.ApplicationConstantes;
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
        if(!formato.toUpperCase().equals(ApplicationConstantes.FORMATO_PDF)
                && !formato.toUpperCase().equals(ApplicationConstantes.FORMATO_DOCX)
                && !formato.toUpperCase().equals(ApplicationConstantes.FORMATO_XLSX))
            return false;
        return true;
    }
}
