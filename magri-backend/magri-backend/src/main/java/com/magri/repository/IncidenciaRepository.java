package com.magri.repository;

import com.magri.entity.Incidencia;
import com.magri.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByUsuario(Usuario usuario);
    List<Incidencia> findByResponsable(Usuario responsable);
    Incidencia findTopByUsuario_IdOrderByFechaRegistroDesc(Integer id); 
}