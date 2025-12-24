package com.magri.controller;

import com.magri.entity.Usuario;
import com.magri.entity.Rol;
import com.magri.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        Usuario user = usuarioRepository.findByEmail(email);
        
        Map<String, Object> response = new HashMap<>();

        if (user == null || !user.getContrasena().equals(password)) {
            response.put("success", false);
            response.put("message", "Credenciales inválidas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Set<Rol> roles = user.getRoles();
        String rolPrincipal = "USER";
        if (roles != null) {
            boolean isAdmin = roles.stream()
                                  .map(Rol::getNombre)
                                  .anyMatch(nombre -> "ADMIN".equalsIgnoreCase(nombre));
            
            if (isAdmin) {
                rolPrincipal = "ADMIN";
            }
        }
        
        response.put("success", true);
        response.put("message", "Login exitoso");
        
        response.put("id_user", user.getId()); 
        response.put("n_user", user.getNombre());   
        
        response.put("rol", rolPrincipal); 
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}