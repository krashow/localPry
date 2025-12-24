package com.magri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magri.entity.IncidenciaSeguimiento;
import com.magri.service.IncidenciaSeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/gestion")
@CrossOrigin(origins = "http://localhost:5173") 
public class RegistroGestionController {

    @Autowired
    private IncidenciaSeguimientoService seguimientoService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/registrar")
    public ResponseEntity<IncidenciaSeguimiento> registrarGestion(
            @RequestParam("seguimiento") String seguimientoJson,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) {
        
        try {
            IncidenciaSeguimiento seguimiento = objectMapper.readValue(seguimientoJson, IncidenciaSeguimiento.class);
            IncidenciaSeguimiento registroGuardado = seguimientoService.guardarSeguimiento(seguimiento, archivo);
            return new ResponseEntity<>(registroGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/historial/{idIncidencia}")
    public ResponseEntity<List<IncidenciaSeguimiento>> obtenerHistorial(
            @PathVariable("idIncidencia") Long idIncidencia) { 
        List<IncidenciaSeguimiento> historial = seguimientoService.obtenerHistorialPorIncidencia(idIncidencia);
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }
}