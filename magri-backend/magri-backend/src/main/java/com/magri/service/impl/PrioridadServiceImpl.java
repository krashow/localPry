package com.magri.service.impl;

import com.magri.entity.Prioridad;
import com.magri.repository.PrioridadRepository;
import com.magri.service.PrioridadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrioridadServiceImpl implements PrioridadService {

    private final PrioridadRepository prioridadRepository;

    public PrioridadServiceImpl(PrioridadRepository prioridadRepository) {
        this.prioridadRepository = prioridadRepository;
    }

    @Override
    public List<Prioridad> listar() {
        return prioridadRepository.findAll();
    }

    @Override
    public Prioridad buscarPorId(Integer id) {
        return prioridadRepository.findById(id).orElse(null);
    }

    @Override
    public Prioridad guardar(Prioridad prioridad) {
        return prioridadRepository.save(prioridad);
    }

    @Override
    public void eliminar(Integer id) {
        prioridadRepository.deleteById(id);
    }
}
