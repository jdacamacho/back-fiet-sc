package com.unicauca.cfiet.solicitudes.infraestructura.configuracion.lectorArchivos;

import com.unicauca.cfiet.solicitudes.aplicacion.output.UsuarioGatewayIntPuerto;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.RolUsuarioDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.TipoUsuarioDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion.UsuarioDTOPeticion;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service("archivos-usuarios")
public class ProcesarArchivoUsuariosImpl implements ProcesadorArchivos<UsuarioDTOPeticion> {
    private final UsuarioGatewayIntPuerto gateway;
    private final ModelMapper mapper;

    public ProcesarArchivoUsuariosImpl( UsuarioGatewayIntPuerto gateway
            , @Qualifier("mapeadorSimple") ModelMapper mapper){
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public List<UsuarioDTOPeticion> procesarArchivo(MultipartFile file) {
        List<UsuarioDTOPeticion> peticiones = new ArrayList<>();
        HashMap<String, TipoUsuarioDTOPeticion> tiposUsuario = cachearTiposDeUsuario();
        HashMap<String, RolUsuarioDTOPeticion> roles = cachearRoles();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                String nombres = row.getCell(0) != null ? row.getCell(0).toString() : "";
                String apellidos = row.getCell(1) != null ? row.getCell(1).toString() : "";
                String tipoDocumento = row.getCell(2) != null ? row.getCell(2).toString() : "";
                String numeroDocumento = row.getCell(3) != null ? String.valueOf((long) row.getCell(3).getNumericCellValue()) : "";
                String telefono = row.getCell(4) != null ? "+" + String.valueOf((long) row.getCell(4).getNumericCellValue()) : "";
                String correo = row.getCell(5) != null ? row.getCell(5).toString() : "";
                String estado = row.getCell(6) != null ? row.getCell(6).toString() : "";
                String username = row.getCell(7) != null ? row.getCell(7).toString() : "";
                String password = row.getCell(8) != null ? row.getCell(8).toString() : "";
                String tipoUsuario = row.getCell(9) != null ? row.getCell(9).toString() : "";
                String rol = row.getCell(10) != null ? row.getCell(10).toString() : "";

                UsuarioDTOPeticion peticion = UsuarioDTOPeticion.builder()
                        .nombres(nombres)
                        .apellidos(apellidos)
                        .tipoDocumento(tipoDocumento)
                        .numeroDocumento(numeroDocumento)
                        .telefono(telefono)
                        .correoElectronico(correo)
                        .estado(Boolean.valueOf(estado))
                        .username(username)
                        .password(password)
                        .objTipoUsuario(tiposUsuario.getOrDefault(tipoUsuario, null))
                        .roles(roles.containsKey(rol) ? List.of(roles.get(rol)) : null)
                        .build();

                peticiones.add(peticion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return peticiones;
    }

    private HashMap<String, TipoUsuarioDTOPeticion> cachearTiposDeUsuario() {
        List<TipoUsuarioDTOPeticion> tipoUsuariosPetions = mapper.map(gateway.getTiposUsuario(), new TypeToken<List<TipoUsuarioDTOPeticion>>(){}.getType());
        return tipoUsuariosPetions.stream()
                .collect(Collectors.toMap(
                        TipoUsuarioDTOPeticion::getNombre,
                        tipo -> tipo,
                        (a, b) -> a,
                        HashMap::new
                ));
    }

    private HashMap<String, RolUsuarioDTOPeticion> cachearRoles() {
        List<RolUsuarioDTOPeticion> rolesPeticion =  mapper.map(gateway.getRoles(), new TypeToken<List<RolUsuarioDTOPeticion>>(){}.getType());
        return rolesPeticion.stream()
                .collect(Collectors.toMap(
                        RolUsuarioDTOPeticion::getNombre,
                        rol -> rol,
                        (a, b) -> a,
                        HashMap::new
                ));
    }

}
