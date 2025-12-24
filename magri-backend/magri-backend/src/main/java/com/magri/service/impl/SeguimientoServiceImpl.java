package com.magri.service.impl;

import com.magri.entity.Seguimiento;
import com.magri.repository.SeguimientoRepository;
import com.magri.service.SeguimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    private final SeguimientoRepository seguimientoRepository;

    public SeguimientoServiceImpl(SeguimientoRepository seguimientoRepository) {
        this.seguimientoRepository = seguimientoRepository;
    }

    @Override
    public List<Seguimiento> listar() {
        return seguimientoRepository.findAll();
    }

    @Override
    public Seguimiento buscarPorId(Integer id) {
        return seguimientoRepository.findById(id).orElse(null);
    }

    @Override
    public Seguimiento guardar(Seguimiento seguimiento) {
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public void eliminar(Integer id) {
        seguimientoRepository.deleteById(id);
    }
}
