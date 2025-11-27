package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.mapeador;

import com.unicauca.cfiet.solicitudes.dominio.modelos.OrdenDelDia;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion.OrdenDelDiaDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTORespuesta.OrdenDelDiaDTORespuesta;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Component
public class MapperSolicitudesInfraestructuraDominio {
    private final ModelMapper mapper;

    public MapperSolicitudesInfraestructuraDominio(@Qualifier("mapeadorSimple") ModelMapper mapper){
        this.mapper = mapper;
    }

    public OrdenDelDia mapearPeticionAModelo(OrdenDelDiaDTOPeticion peticion){
        return mapper.map(peticion, OrdenDelDia.class);
    }

    public List<OrdenDelDia> mapearPeticionesAModelo(List<OrdenDelDiaDTOPeticion> peticiones){
        return mapper.map(peticiones, new TypeToken<List<OrdenDelDia>>(){}.getType());
    }

    public OrdenDelDiaDTORespuesta mapearModeloARespuesta(OrdenDelDia modelo){
        return mapper.map(modelo, OrdenDelDiaDTORespuesta.class);
    }

    public List<OrdenDelDiaDTORespuesta> mapearModelosARespuesta(List<OrdenDelDia> modelos){
        return mapper.map(modelos, new TypeToken<List<OrdenDelDiaDTORespuesta>>(){}.getType());
    }

}
