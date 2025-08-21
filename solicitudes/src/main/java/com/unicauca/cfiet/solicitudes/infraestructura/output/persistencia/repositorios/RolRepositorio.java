package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.repositorios;

import com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades.RolEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository <RolEntidad, String> {

}
