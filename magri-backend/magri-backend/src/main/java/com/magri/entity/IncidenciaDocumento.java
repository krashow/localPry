package com.magri.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidencia_documentos")
public class IncidenciaDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_img")
    private Long idImg;
    
    @Column(name = "nombre_estructurado")
    private String nombreEstructurado;
    
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    
    @Column(name = "id_inc")
    private Integer idInc;
    
    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida;
    public IncidenciaDocumento() {
        this.fechaSubida = LocalDateTime.now();
    }

	public Long getIdImg() {
		return idImg;
	}

	public void setIdImg(Long idImg) {
		this.idImg = idImg;
	}

	public String getNombreEstructurado() {
		return nombreEstructurado;
	}

	public void setNombreEstructurado(String nombreEstructurado) {
		this.nombreEstructurado = nombreEstructurado;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public Integer getIdInc() {
		return idInc;
	}

	public void setIdInc(Integer idInc) {
		this.idInc = idInc;
	}

	public LocalDateTime getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(LocalDateTime fechaSubida) {
		this.fechaSubida = fechaSubida;
	}
}