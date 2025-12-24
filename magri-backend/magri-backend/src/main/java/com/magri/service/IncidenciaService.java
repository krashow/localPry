package com.magri.service;

import com.magri.dto.IncidenciaRequest;
import com.magri.dto.AsignarIncidenciaRequest;
import com.magri.entity.Incidencia;
import com.magri.entity.Usuario;
import java.util.List;

public interface IncidenciaService {
    List<Incidencia> listar();
    Incidencia buscarPorId(Long id); 
    
    Incidencia guardar(Incidencia incidencia);
    
    void eliminar(Integer id);
	void eliminar(Long id); 
    
	void registrarIncidencia(IncidenciaRequest request);
	Incidencia asignarResponsable(Integer idIncidencia, Integer idResponsable, String observacion);
	
	List<Incidencia> listarPorResponsable(Usuario responsable); 
	List<Incidencia> listarPorUsuarioReportante(Usuario reportante);
	Incidencia buscarUltimaIncidenciaPorUsuario(Integer idUsuario);
}