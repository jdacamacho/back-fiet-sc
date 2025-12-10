package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.mapeador;

import com.unicauca.cfiet.solicitudes.dominio.modelos.*;
import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Mapper {

    @Bean("mapeadorSimple")
    public ModelMapper crearMapeadorSimple() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setCollectionsMergeEnabled(false);
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setPropertyCondition(ctx -> true);
        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.createTypeMap(UsuarioEntidad.class, Usuario.class)
                .addMappings(m -> {
                    m.map(UsuarioEntidad::getObjTipoUsuario, Usuario::setObjTipoUsuario);
                    m.map(UsuarioEntidad::getRoles, Usuario::setRoles);
                    m.map(UsuarioEntidad::getLogs, Usuario::setLogs);
                });

        mapper.createTypeMap(TipoUsuarioEntidad.class, TipoUsuario.class)
                .addMappings(m -> m.skip(TipoUsuario::setUsuarios));


        mapper.createTypeMap(TipoSolicitudEntidad.class, TipoSolicitud.class)
                .addMappings(m -> {
                    m.map(TipoSolicitudEntidad::getObjFuncionarioEncargado,
                            TipoSolicitud::setObjFuncionarioEncargado);
                    m.skip(TipoSolicitud::setAnexos);
                })
                .setPostConverter(ctx -> {
                    TipoSolicitudEntidad src = ctx.getSource();
                    TipoSolicitud dest = ctx.getDestination();

                    if (src.getAnexos() != null) {
                        List<TipoAnexo> anexos = new ArrayList<>();
                        for (TipoAnexoEntidad a : src.getAnexos()) {
                            TipoAnexo anexo = mapper.map(a, TipoAnexo.class);
                            anexos.add(anexo);
                        }
                        dest.setAnexos(anexos);
                    }

                    return dest;
                });

        mapper.createTypeMap(TipoAnexoEntidad.class, TipoAnexo.class)
                .addMappings(m -> m.skip(TipoAnexo::setObjTipoSolicitud));


        return mapper;
    }
}
