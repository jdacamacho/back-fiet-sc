package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios;

import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.SolicitudEntidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Repository
public interface SolicitudRepositorio extends JpaRepository<SolicitudEntidad, String> {
    @Query("""
    SELECT s FROM SolicitudEntidad s
    WHERE LOWER(s.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
    """)
    Page<SolicitudEntidad> buscarPorNombre(
            @Param("filtro") String filtro,
            Pageable pageable
    );

    @Query("""
    SELECT s FROM SolicitudEntidad s
    WHERE s.objFuncionario.uuidUsuario = :uuidUsuario
    AND LOWER(s.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
    """)
    Page<SolicitudEntidad> buscarPorNombreYFuncionario(
            @Param("uuidUsuario") String uuidUsuario,
            @Param("filtro") String filtro,
            Pageable pageable
    );


    List<SolicitudEntidad> findByEstadoIgnoreCase(String estado);

    Page<SolicitudEntidad> findByObjFuncionarioUuidUsuario(
            String uuidUsuario,
            Pageable pageable
    );

    List<SolicitudEntidad> findByObjOrdenDelDiaUuidOrdenDelDia(
            String uuidOrdenDelDia
    );
}
