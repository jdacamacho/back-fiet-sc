package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.controlador;

import com.unicauca.cfiet.solicitudes.infraestructura.configuracion.swagger.json.RolesJson;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.DTOPeticion.RolDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorRoles.DTORespuesta.RolDTORespuesta;
import com.unicauca.cfiet.solicitudes.infraestructura.configuracion.swagger.json.ErrorJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Interface del controlador con la documentación de swagger
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
public interface IRolRestController {
    @Operation(
            summary = "Listar roles",
            description = "Obtiene una lista paginada de roles según el número de página y tamaño especificados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de roles obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RolDTORespuesta.class)),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta exitosa",
                                    value = RolesJson.ROLES
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error formato invalido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de error 400",
                                    value = ErrorJson.ERROR_RESPONSE_400
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de error 500",
                                    value = ErrorJson.ERROR_RESPONSE_500
                            )
                    )
            )
    })
    public ResponseEntity<List<RolDTORespuesta>> index(
            @Parameter(description = "Número de página (empezando desde 0)")
            int pagina,
            @Parameter(description = "Cantidad de elementos por página")
            int tamanio
    );

    @Operation(
            summary = "Obtener rol por UUID",
            description = "Obtiene la información de un rol específico a partir de su identificador único."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Rol obtenido correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RolDTORespuesta.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta exitosa",
                                    value = RolesJson.ROL
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error formato invalido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de error 400",
                                    value = ErrorJson.ERROR_RESPONSE_400
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de error 500",
                                    value = ErrorJson.ERROR_RESPONSE_500
                            )
                    )
            )
    })
    public ResponseEntity<RolDTORespuesta> getRol(@Parameter(description = "uuid del rol a consultar") String uuid);

    @Operation(
            summary = "Actualizar un rol",
            description = "Permite actualizar la descripción y el estado de un rol existente",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RolDTOPeticion.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de petición",
                                    value = RolesJson.ROL_PETICION
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rol actualizado correctamente",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RolDTORespuesta.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de respuesta exitosa",
                                            value = RolesJson.ROL
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Error de formato inválido",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de error 400",
                                            value = ErrorJson.ERROR_RESPONSE_400
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class),
                                    examples = @ExampleObject(
                                            name = "Ejemplo de error 500",
                                            value = ErrorJson.ERROR_RESPONSE_500
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<RolDTORespuesta> actualizarRol(String uuid, RolDTOPeticion rolPeticion);
}
