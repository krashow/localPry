package com.magri.service;

import com.magri.entity.Prioridad;
import java.util.List;

public interface PrioridadService {
    List<Prioridad> listar();
    Prioridad buscarPorId(Integer id);
    Prioridad guardar(Prioridad prioridad);
    void eliminar(Integer id);
}
