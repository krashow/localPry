package com.magri.service;

import com.magri.entity.Rol;
import java.util.List;

public interface RolService {
    List<Rol> listar();
    Rol buscarPorId(Integer id);
    Rol guardar(Rol rol);
    void eliminar(Integer id);
}
