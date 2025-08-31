package com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones;

/**
 *  Definición de mensajes de error
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class MensajesError {
    public static final String PAGINACION_ERROR = "Error en la paginación o tamaño de la pagina...";
    public static final String SIN_REGISTROS = "No existen registrados %s en el sistema...";
    public static final String ARCHIVO_EXCEL_VACIO = "No se pudo procesar %s de la petición";
    public static final String ENTIDAD_NO_ENCONTRADA = "%s con id %s no fue encontrado en el sistema...";
    public static final String ATRIBUTO_UNICO_YA_EXISTE = "%s con %s: %s existe en el sistema...";
    public static final String ENTIDAD_NO_ENCONTRADA_FILTRO = "%s con %s: %s no fue encontrado en el sistema...";
    public static final String INSTANCIA_NO_VALIDA = "Se intentó crear una instancia de %s no permitida...";
    public static final String ROLES_DUPLICADOS_USUARIO = "El usuario tiene roles duplicados...";
    public static final String ROLES_NO_VALIDOS = "Los roles ingresados no son validos...";
    public static final String CONTRASEÑA_INCORRECTA = "Contraseña incorrecta...";
    public static final String CREDENCIALES_ERRONEAS = "Credenciales erroneas, revise su username o contraseña...";
}
