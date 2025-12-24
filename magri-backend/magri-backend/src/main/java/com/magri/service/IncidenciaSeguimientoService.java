package com.magri.service;

import com.magri.entity.IncidenciaSeguimiento;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface IncidenciaSeguimientoService {
    IncidenciaSeguimiento guardarSeguimiento(IncidenciaSeguimiento seguimiento, MultipartFile archivo) throws Exception;
    List<IncidenciaSeguimiento> obtenerHistorialPorIncidencia(Long idIncidencia);
}