package com.magri.service;

import com.magri.entity.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario buscarPorId(Integer id);
    Usuario guardar(Usuario usuario);
    void eliminar(Integer id);
    Usuario buscarPorUsername(String username);
    Usuario buscarPorEmail(String email);
}
