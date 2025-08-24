package com.unicauca.cfiet.solicitudes.infraestructura.configuracion;

import com.unicauca.cfiet.solicitudes.aplicacion.output.ExcepcionesFormateadorIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.RolGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.UsuarioGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.domain.casosdeuso.RolCUImplAdaptador;
import com.unicauca.cfiet.solicitudes.domain.casosdeuso.UsuarioCUImplAdaptador;
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
    public RolCUImplAdaptador createRolCU(RolGatewayIntPuerto gateway, ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        return new RolCUImplAdaptador(gateway, formateadorExcepciones);
    }

    @Bean
    public UsuarioCUImplAdaptador createUsuarioCU(UsuarioGatewayIntPuerto gateway, ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        return new UsuarioCUImplAdaptador(gateway, formateadorExcepciones);
    }
}
