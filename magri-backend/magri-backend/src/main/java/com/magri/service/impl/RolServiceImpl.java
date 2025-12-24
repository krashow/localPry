package com.magri.service.impl;

import com.magri.entity.Rol;
import com.magri.repository.RolRepository;
import com.magri.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminar(Integer id) {
        rolRepository.deleteById(id);
    }
}
