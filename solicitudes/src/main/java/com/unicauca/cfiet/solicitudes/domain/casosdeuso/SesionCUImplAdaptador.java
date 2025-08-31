package com.unicauca.cfiet.solicitudes.domain.casosdeuso;

import com.unicauca.cfiet.solicitudes.aplicacion.input.SesionCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.SesionGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioTokenizado;
import com.unicauca.cfiet.solicitudes.infraestructura.output.manejadorExcepciones.MensajesError;

/**
 * Implementación de la interfaz de los casos de uso para sesiones.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public class SesionCUImplAdaptador implements SesionCUIntPuerto{
    private final SesionGatewayIntPuerto gateway;
    private  final ExcepcionesFormateadorIntPuerto formateadorExcepciones;

    public SesionCUImplAdaptador(SesionGatewayIntPuerto gateway,
                                 ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        this.gateway = gateway;
        this.formateadorExcepciones = formateadorExcepciones;
    }

    @Override
    public UsuarioTokenizado login(String username, String contraseña) {
        String token = gateway.login(username, contraseña);
        if(token == null)
            formateadorExcepciones.lanzarCredencialesErroneas(MensajesError.CREDENCIALES_ERRONEAS);
        Usuario usuario = gateway.getUsuario(username);
        return usuario.tokenizarObjecto(token);
    }
}
