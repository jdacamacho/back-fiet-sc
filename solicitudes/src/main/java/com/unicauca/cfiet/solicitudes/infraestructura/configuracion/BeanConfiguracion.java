package com.unicauca.cfiet.solicitudes.infraestructura.configuracion;

import com.unicauca.cfiet.solicitudes.aplicacion.input.LogCUIntPuerto;
import com.unicauca.cfiet.solicitudes.aplicacion.output.*;
import com.unicauca.cfiet.solicitudes.domain.casosdeuso.LogCUImplAdaptador;
import com.unicauca.cfiet.solicitudes.domain.casosdeuso.RolCUImplAdaptador;
import com.unicauca.cfiet.solicitudes.domain.casosdeuso.SesionCUImplAdaptador;
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
    public RolCUImplAdaptador createRolCU(RolGatewayIntPuerto gateway, ExcepcionesFormateadorIntPuerto formateadorExcepciones, LogCUIntPuerto logCU){
        return new RolCUImplAdaptador(gateway, formateadorExcepciones, logCU);
    }

    @Bean
    public UsuarioCUImplAdaptador createUsuarioCU(UsuarioGatewayIntPuerto gateway,
                                                  ExcepcionesFormateadorIntPuerto formateadorExcepciones,
                                                  LogCUIntPuerto logCU,
                                                  PasswordEncoderGatewayIntPuerto encoder){
        return new UsuarioCUImplAdaptador(gateway, formateadorExcepciones, logCU, encoder);
    }

    @Bean
    public SesionCUImplAdaptador createSesionCU(SesionGatewayIntPuerto gateway,
                                                LogCUIntPuerto logCU,
                                                ExcepcionesFormateadorIntPuerto formateadorExcepciones){
        return new SesionCUImplAdaptador(gateway, logCU, formateadorExcepciones);
    }
    @Bean
    public LogCUImplAdaptador crearLogCU(LogGatewayIntPuerto gateway,ExcepcionesFormateadorIntPuerto formateadorExcepciones, IJwtServicio jwtServicio){
        return new LogCUImplAdaptador(gateway, formateadorExcepciones, jwtServicio);
    }
}
