package com.magri.service;

import com.magri.entity.Seguimiento;
import java.util.List;

public interface SeguimientoService {
    List<Seguimiento> listar();
    Seguimiento buscarPorId(Integer id);
    Seguimiento guardar(Seguimiento seguimiento);
    void eliminar(Integer id);
}
