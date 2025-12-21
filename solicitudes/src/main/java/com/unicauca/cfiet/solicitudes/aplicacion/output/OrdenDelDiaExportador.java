package com.unicauca.cfiet.solicitudes.aplicacion.output;

import java.util.HashMap;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface OrdenDelDiaExportador {
    byte[] exportarOrdenDelDia(HashMap<String, String> data);
}
