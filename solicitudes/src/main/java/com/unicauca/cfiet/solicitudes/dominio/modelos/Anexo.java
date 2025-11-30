package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class Anexo {
    private String uuidAnexo;
    private String nombre;
    private String urlAnexo;
    private Solicitud objSolicitud;
}
