package com.magri.repository;

import com.magri.entity.IncidenciaSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaSeguimientoRepository extends JpaRepository<IncidenciaSeguimiento, Long> {
    
    List<IncidenciaSeguimiento> findByIdIncidenciaOrderByFechaRegistroAsc(Long idIncidencia);
}