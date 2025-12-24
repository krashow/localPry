package com.magri.repository;

import com.magri.entity.IncidenciaDocumento;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaDocumentoRepository extends JpaRepository<IncidenciaDocumento, Long> {
	List<IncidenciaDocumento> findByIdInc(Integer idInc);
}