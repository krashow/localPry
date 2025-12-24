package com.magri.service;

import com.magri.entity.Area;
import java.util.List;

public interface AreaService {
    List<Area> listar();
    Area buscarPorId(Integer id);
    Area guardar(Area area);
    void eliminar(Integer id);
}
