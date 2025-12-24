package com.magri.service;

import com.magri.entity.Categoria;
import java.util.List;
public interface CategoriaService {
    List<Categoria> listar();
    Categoria buscarPorId(Integer id);
    Categoria guardar(Categoria categoria);
    void eliminar(Integer id);
}