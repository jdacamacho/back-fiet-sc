package com.unicauca.cfiet.solicitudes.infraestructura.configuracion;

import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.RolGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.casosdeuso.RolCUImplAdaptador;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gestiona las implementaciones de los casos de uso.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Configuration
public class BeanConfiguracion {

    @Bean
    public RolCUImplAdaptador createRoleCU(RolGatewayIntPuerto gateway, ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        return new RolCUImplAdaptador(gateway, formateadorExcepciones);
    }
}
