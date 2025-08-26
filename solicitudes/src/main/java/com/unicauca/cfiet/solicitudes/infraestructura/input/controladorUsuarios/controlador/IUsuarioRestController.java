package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.controlador;

import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioActualizarDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioLivianoDTORespuesta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface del controlador con la documentación de swagger para gestión de Usuarios.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface IUsuarioRestController {
    public ResponseEntity<List<UsuarioLivianoDTORespuesta>> index(int pagina, int tamanio);
    public ResponseEntity<UsuarioDTORespuesta> getUsuario(String uuid);
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDTOPeticion peticion, String tipoUsuario, BindingResult result);
    public ResponseEntity<?> actualizarUsuario(String uuid, @Valid @RequestBody UsuarioActualizarDTOPeticion peticion, BindingResult result);
}
