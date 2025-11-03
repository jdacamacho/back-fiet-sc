package com.unicauca.cfiet.solicitudes.infraestructura.configuracion.lectorArchivos;

import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.DTOPeticion.TipoAnexoDTOPeticion;
import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorTiposSolicitud.DTOPeticion.TipoSolicitudDTOPeticion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Crea las peticiones para crear tipos de solicitudes leidos del archivo excel.
 *
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service("archivos-tipos-solicitudes")
public class ProcesarArchivoTiposSolicitudesImpl implements ProcesadorArchivos<TipoSolicitudDTOPeticion>{
    private final ModelMapper mapper;

    public ProcesarArchivoTiposSolicitudesImpl(@Qualifier("mapeadorSimple") ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public List<TipoSolicitudDTOPeticion> procesarArchivo(MultipartFile file) {
        List<TipoSolicitudDTOPeticion> peticiones = new ArrayList<>();
        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                if (filaVacia(row)) break;
                String nombreTipoSolicitud = row.getCell(0) != null ? row.getCell(0).toString() : "";
                String descripcionTipoSolicitud = row.getCell(1) != null ? row.getCell(1).toString() : "";
                String seccionTipoSolicitud = row.getCell(2) != null ? row.getCell(2).toString() : "";
                String nombreTipoAnexo = row.getCell(3) != null ? row.getCell(3).toString() : "";
                String descripcionTipoAnexo = row.getCell(4) != null ? row.getCell(4).toString() : "";
                String formatoTipoAnexo = row.getCell(5) != null ? row.getCell(5).toString() : "";
                String obligatoriedadTipoAnexo = row.getCell(6) != null ? row.getCell(6).toString() : "";
                String uuidFuncionario = row.getCell(7) != null ? row.getCell(7).toString() : "";

                TipoAnexoDTOPeticion tipoAnexoPeticion = TipoAnexoDTOPeticion.builder()
                        .nombre(nombreTipoAnexo)
                        .descripcion(descripcionTipoAnexo)
                        .formato(formatoTipoAnexo)
                        .obligatoriedad(Boolean.valueOf(obligatoriedadTipoAnexo))
                        .build();

                TipoSolicitudDTOPeticion tipoSolicitudPeticion = TipoSolicitudDTOPeticion.builder()
                        .nombre(nombreTipoSolicitud)
                        .descripcion(descripcionTipoSolicitud)
                        .seccion(seccionTipoSolicitud)
                        .anexos(List.of(tipoAnexoPeticion))
                        .uuidFuncionario(uuidFuncionario)
                        .build();

                peticiones.add(tipoSolicitudPeticion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peticiones;
    }

    private boolean filaVacia(Row row) {
        if (row == null) return true;
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !cell.toString().trim().isEmpty())
                return false;
        }
        return true;
    }
}
