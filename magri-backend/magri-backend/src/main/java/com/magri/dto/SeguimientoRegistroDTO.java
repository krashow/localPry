package com.magri.dto;

import lombok.Data;
import java.time.LocalDate;

@Data // Genera Getters y Setters
public class SeguimientoRegistroDTO {
    
    private String tipo;
    private String descripcion;
    private String nuevoEstado;
    private String tiempoInvertido;
    private LocalDate fechaCompromiso;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNuevoEstado() {
		return nuevoEstado;
	}
	public void setNuevoEstado(String nuevoEstado) {
		this.nuevoEstado = nuevoEstado;
	}
	public String getTiempoInvertido() {
		return tiempoInvertido;
	}
	public void setTiempoInvertido(String tiempoInvertido) {
		this.tiempoInvertido = tiempoInvertido;
	}
	public LocalDate getFechaCompromiso() {
		return fechaCompromiso;
	}
	public void setFechaCompromiso(LocalDate fechaCompromiso) {
		this.fechaCompromiso = fechaCompromiso;
	}
    
}