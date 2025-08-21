package com.unicauca.cfiet.solicitudes.aplicacion.output;

/**
 *  Interface para manejar excepciones
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface ExcepcionesFormateadorIntPuerto {
    /**
     * Método para devolver un mensaje de error cuando no existe la entidad.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public void lanzarEntidadNoExiste(String mensaje);

    /**
     * Método para devolver un mensaje de error cuando la entidad esta duplicada.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public void lanzarEntidadExiste(String mensaje);

    /**
     * Método para devolver un mensaje de error cuando una regla de negocio fue violada.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public void lanzarReglaNegocioViolada(String mensaje);

    /**
     * Método para devolver un mensaje de error cuando fallo la autenticación del usuario.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public void lanzarCredencialesErroneas(String mensaje);

    /**
     * Método para devolver un mensaje de error cuando un formato es erroneo.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public void lanzarMalFormato(String mensaje);

    /**
     * Método para devolver un mensaje de error cuando una consulta no obtuvo información.
     *
     * @param mensaje Mensaje descriptivo del error.
     */
    public void lanzarSinInformacion(String mensaje);
}