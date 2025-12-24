package com.magri.repository;

import com.magri.entity.Seguimiento;
import com.magri.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
    List<Seguimiento> findByIncidencia(Incidencia incidencia);
}
