package com.magri.repository;

import com.magri.entity.PreguntaIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PreguntaIncidenciaRepository extends JpaRepository<PreguntaIncidencia, Long> {
    List<PreguntaIncidencia> findByCategoriaId(Long idCategoria);
}
