package com.magri.controller;

import com.magri.entity.Usuario;
import com.magri.repository.UsuarioRepository;
import com.magri.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap; 
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
    private UsuarioRepository usuarioRepository; 

    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
    }
    @GetMapping("/buscar/username/{username}")
    public Usuario buscarPorUsername(@PathVariable String username) {
        return usuarioService.buscarPorUsername(username);
    }

    @GetMapping("/buscar/email/{email}")
    public Usuario buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }
    
    @GetMapping("/responsables")
    public ResponseEntity<List<Map<String, Object>>> getResponsables() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        
        List<Map<String, Object>> responsablesSimplificados = usuarios.stream()
            .map(usuario -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", usuario.getId());
                map.put("nombre", usuario.getNombre());
                map.put("username", usuario.getUsername());
                map.put("email", usuario.getEmail());
                return map;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(responsablesSimplificados);
    }
}
