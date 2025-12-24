package com.magri.controller;

import com.magri.entity.Estado;
import com.magri.service.EstadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/{id}")
    public Estado buscarPorId(@PathVariable Integer id) {
        return estadoService.buscarPorId(id);
    }

    @PostMapping
    public Estado guardar(@RequestBody Estado estado) {
        return estadoService.guardar(estado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        estadoService.eliminar(id);
    }
}
