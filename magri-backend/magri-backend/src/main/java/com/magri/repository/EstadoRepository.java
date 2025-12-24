package com.magri.repository;

import com.magri.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	Optional<Estado> findByTipo(String tipo);  
}

