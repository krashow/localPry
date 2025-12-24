package com.magri.service;

import com.magri.entity.Estado;
import java.util.List;

public interface EstadoService {
    List<Estado> listar();
    Estado buscarPorId(Integer id);
    Estado guardar(Estado estado);
    void eliminar(Integer id);
}
