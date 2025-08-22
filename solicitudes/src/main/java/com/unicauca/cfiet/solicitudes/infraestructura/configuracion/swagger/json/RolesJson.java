package com.unicauca.cfiet.solicitudes.infraestructura.configuracion.swagger.json;

/**
 * JSON con ejemplos para documentación swagger.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class RolesJson {
    public static final String ROL_PETICION = """
        {
            "descripcion": "String",
            "estado": true
          }
        """;

    public static final String ROLES = """
        [
          {
            "uuid": "String",
            "nombre": "String",
            "descripcion": "String",
            "estado": true
          },
          {
            "uuid": "String",
            "nombre": "String",
            "descripcion": "String",
            "estado": true
          }
        ]
        """;

    public static final String ROL = """
        {
            "uuid": "String",
            "nombre": "String",
            "descripcion": "String",
            "estado": true
          }
        """;
}
