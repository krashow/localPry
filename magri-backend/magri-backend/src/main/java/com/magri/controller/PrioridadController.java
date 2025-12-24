package com.magri.controller;

import com.magri.entity.Prioridad;
import com.magri.service.PrioridadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prioridades")
public class PrioridadController {

    private final PrioridadService prioridadService;

    public PrioridadController(PrioridadService prioridadService) {
        this.prioridadService = prioridadService;
    }

    @GetMapping
    public List<Prioridad> listar() {
        return prioridadService.listar();
    }

    @GetMapping("/{id}")
    public Prioridad buscarPorId(@PathVariable Integer id) {
        return prioridadService.buscarPorId(id);
    }

    @PostMapping
    public Prioridad guardar(@RequestBody Prioridad prioridad) {
        return prioridadService.guardar(prioridad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        prioridadService.eliminar(id);
    }
}
