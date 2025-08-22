package com.unicauca.cfiet.solicitudes.infraestructura.configuracion.swagger.json;

/**
 * Json de error para Swagger.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class ErrorJson {
    public static final String ERROR_RESPONSE_400 = """
        {
          "codigoError": "String",
          "mensaje": "String",
          "httpCodigo": 400,
          "url": "String",
          "metodo": "String"
        }
        """;

    public static final String ERROR_RESPONSE_500 = """
        {
          "codigoError": "String",
          "mensaje": "String",
          "httpCodigo": 500,
          "url": "String",
          "metodo": "String"
        }
        """;
}
