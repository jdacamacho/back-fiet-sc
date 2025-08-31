package com.unicauca.cfiet.solicitudes.infraestructura.output.JWT;

import com.unicauca.cfiet.solicitudes.aplicacion.output.IJwtServicio;
import com.unicauca.cfiet.solicitudes.infraestructura.configuracion.seguridad.jwt.JwtServicio;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class JwtServicioImpl implements IJwtServicio {
    private final JwtServicio jwt;

    public JwtServicioImpl(JwtServicio jwt){
        this.jwt = jwt;
    }

    @Override
    public String getUsername(String token) {
        return jwt.getUsernameFromToken(token);
    }
}
