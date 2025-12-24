package com.magri.controller;

import com.magri.entity.Seguimiento;
import com.magri.service.SeguimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguimientos")
public class SeguimientoController {

    private final SeguimientoService seguimientoService;

    public SeguimientoController(SeguimientoService seguimientoService) {
        this.seguimientoService = seguimientoService;
    }

    @GetMapping
    public List<Seguimiento> listar() {
        return seguimientoService.listar();
    }

    @GetMapping("/{id}")
    public Seguimiento buscarPorId(@PathVariable Integer id) {
        return seguimientoService.buscarPorId(id);
    }

    @PostMapping
    public Seguimiento guardar(@RequestBody Seguimiento seguimiento) {
        return seguimientoService.guardar(seguimiento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        seguimientoService.eliminar(id);
    }
}
