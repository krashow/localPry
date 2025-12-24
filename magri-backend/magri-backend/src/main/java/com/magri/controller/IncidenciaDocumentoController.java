package com.magri.controller;

import com.magri.entity.IncidenciaDocumento;
import com.magri.repository.IncidenciaDocumentoRepository;
import org.springframework.core.io.Resource; 
import org.springframework.core.io.UrlResource; 
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class IncidenciaDocumentoController { 
    
    private final IncidenciaDocumentoRepository repository;
    private final String UPLOAD_DIR = "D:\\imgInc"; 
    
    public IncidenciaDocumentoController(IncidenciaDocumentoRepository repository) {
        this.repository = repository;
    }
    @PostMapping("/upload-documento")
    public ResponseEntity<Map<String, Object>> uploadFile(
        @RequestParam("file") MultipartFile file, 
        @RequestParam("idIncidencia") Integer idIncidencia, 
        @RequestParam("numeroDocumento") Integer numeroDocumento 
    ) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("success", false);
            response.put("message", "El archivo está vacío.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".dat";
            String tipo = (file.getContentType() != null && file.getContentType().contains("image")) ? "cap" : "doc";
            String nombreEstructurado = String.format("%s_%02d_incidencia#%d%s", tipo, numeroDocumento, idIncidencia, extension);

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); 
            }
            Path filePath = uploadPath.resolve(nombreEstructurado);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            IncidenciaDocumento doc = new IncidenciaDocumento();
            doc.setNombreEstructurado(nombreEstructurado);
            doc.setRutaArchivo(filePath.toString());
            doc.setIdInc(idIncidencia);
            repository.save(doc);
            
            response.put("success", true);
            response.put("message", "Documento subido y ligado correctamente.");
            response.put("id_img", doc.getIdImg());
            response.put("nombre_guardado", nombreEstructurado);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Error al procesar la subida del documento: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
    @GetMapping("/documentos/{idIncidencia}")
    public ResponseEntity<List<IncidenciaDocumento>> getDocumentosByIncidencia(
        @PathVariable("idIncidencia") Integer idInc
    ) {
        try {
            List<IncidenciaDocumento> documentos = repository.findByIdInc(idInc);
            return ResponseEntity.ok(documentos);
        } catch (Exception e) {
            System.err.println("---------------------------------------------------------");
            System.err.println("ERROR CRÍTICO AL BUSCAR DOCUMENTOS: " + e.getMessage());
            e.printStackTrace();
            System.err.println("---------------------------------------------------------");
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/documentos/descargar/{nombreArchivo}")
    public ResponseEntity<Resource> downloadDocument(
        @PathVariable("nombreArchivo") String nombreArchivo
    ) {
        try {
            if (nombreArchivo.contains("..") || nombreArchivo.contains("/") || nombreArchivo.contains("\\")) {
                return ResponseEntity.badRequest().build();
            }
            Path filePath = Paths.get(UPLOAD_DIR).resolve(nombreArchivo).normalize(); 
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }      
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/documentos/visualizar/{nombreArchivo}")
    public ResponseEntity<Resource> viewDocument(
        @PathVariable("nombreArchivo") String nombreArchivo 
    ) {
        try {
            if (nombreArchivo.contains("..") || nombreArchivo.contains("/") || nombreArchivo.contains("\\")) {
                return ResponseEntity.badRequest().build();
            }  
            Path filePath = Paths.get(UPLOAD_DIR).resolve(nombreArchivo).normalize(); 
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}