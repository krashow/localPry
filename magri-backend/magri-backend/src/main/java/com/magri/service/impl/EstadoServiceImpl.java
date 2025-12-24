package com.magri.service.impl;

import com.magri.entity.Estado;
import com.magri.repository.EstadoRepository;
import com.magri.service.EstadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoServiceImpl(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @Override
    public Estado buscarPorId(Integer id) {
        return estadoRepository.findById(id).orElse(null);
    }

    @Override
    public Estado guardar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Override
    public void eliminar(Integer id) {
        estadoRepository.deleteById(id);
    }
}
