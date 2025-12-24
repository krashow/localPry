package com.magri.service.impl;

import com.magri.entity.IncidenciaSeguimiento;
import com.magri.repository.IncidenciaSeguimientoRepository;
import com.magri.service.IncidenciaSeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class IncidenciaSeguimientoServiceImpl implements IncidenciaSeguimientoService {

    @Autowired
    private IncidenciaSeguimientoRepository seguimientoRepository;

    @Override
    public IncidenciaSeguimiento guardarSeguimiento(IncidenciaSeguimiento seguimiento, MultipartFile archivo) throws Exception {
        
        if (seguimiento.getFechaRegistro() == null) {
            seguimiento.setFechaRegistro(LocalDateTime.now()); 
        }

        if (archivo != null && !archivo.isEmpty()) {
            String folder = "uploads/seguimientos";
            Path root = Paths.get(folder);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String nombreFichero = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Files.copy(archivo.getInputStream(), root.resolve(nombreFichero), StandardCopyOption.REPLACE_EXISTING);
            
            seguimiento.setAdjuntoRuta(folder + "/" + nombreFichero);
        }

        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public List<IncidenciaSeguimiento> obtenerHistorialPorIncidencia(Long idIncidencia) {
        return seguimientoRepository.findByIdIncidenciaOrderByFechaRegistroAsc(idIncidencia);
    }
}