package com.magri.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seguimiento")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seg")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_inc")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @Column(name = "fec_seg")
    private LocalDateTime fecha;

    @Column(name = "desc_seg", nullable = false)
    private String descripcion;

    @Column(name = "obs_seg")
    private String observacion;

    @Column(name = "tipo_accion", length = 100)
    private String tipoAccion;
    // Getters y setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}
}
