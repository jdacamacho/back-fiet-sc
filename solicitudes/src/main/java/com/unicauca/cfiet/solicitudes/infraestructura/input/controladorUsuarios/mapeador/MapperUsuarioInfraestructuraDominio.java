package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.mapeador;

import com.unicauca.cfiet.solicitudes.domain.modelos.Usuario;
import com.unicauca.cfiet.solicitudes.domain.modelos.UsuarioLiviano;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioActualizarDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioLivianoDTORespuesta;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Component
public class MapperUsuarioInfraestructuraDominio {
    private  final ModelMapper mapper;

    public MapperUsuarioInfraestructuraDominio(@Qualifier("mapeadorSimple") ModelMapper mapper){
        this.mapper = mapper;
    }

    public Usuario mapearPeticionAModelo(UsuarioDTOPeticion peticion){
        return mapper.map(peticion, Usuario.class);
    }

    public Usuario mapearPeticionActualizarAModelo(UsuarioActualizarDTOPeticion peticion){
        return mapper.map(peticion, Usuario.class);
    }

    public UsuarioDTORespuesta mapearModeloARespuesta(Usuario modelo){
        return mapper.map(modelo, UsuarioDTORespuesta.class);
    }

    public List<UsuarioLivianoDTORespuesta> mapearModelosARespuesta(List<UsuarioLiviano> modelos){
        return mapper.map(modelos, new TypeToken<List<UsuarioLivianoDTORespuesta>>(){}.getType());
    }
}
