package com.magri.dto;

import java.util.Map;

public class IncidenciaRequest {
	private Integer idCategoria; 
	private Integer idArea;
	private String observacion;
    private Map<Long, String> respuestas;
    private Integer idPrioridad; 
    private Integer idUsuarioReportante; 

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Map<Long, String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<Long, String> respuestas) {
        this.respuestas = respuestas;
    }
    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }
    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

	public Integer getIdPrioridad() {
		return idPrioridad;
	}

	public void setIdPrioridad(Integer idPrioridad) {
		this.idPrioridad = idPrioridad;
	}
    public Integer getIdUsuarioReportante() {
        return idUsuarioReportante;
    }

    public void setIdUsuarioReportante(Integer idUsuarioReportante) {
        this.idUsuarioReportante = idUsuarioReportante;
    }
}