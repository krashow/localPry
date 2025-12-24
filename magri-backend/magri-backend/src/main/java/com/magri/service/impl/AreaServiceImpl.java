package com.magri.service.impl;

import com.magri.entity.Area;
import com.magri.repository.AreaRepository;
import com.magri.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override
    public List<Area> listar() {
        return areaRepository.findAll();
    }

    @Override
    public Area buscarPorId(Integer id) {
        return areaRepository.findById(id).orElse(null);
    }

    @Override
    public Area guardar(Area area) {
        return areaRepository.save(area);
    }

    @Override
    public void eliminar(Integer id) {
        areaRepository.deleteById(id);
    }
}
