package com.magri.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidencia_seguimientos")
public class IncidenciaSeguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_inc", nullable = false)
    private Long idIncidencia;
    
    @Column(name = "id_user", nullable = false)
    private Long idUsuario;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo; 

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "nuevo_estado", length = 50)
    private String nuevoEstado;

    @Column(name = "tiempo_invertido", length = 20)
    private String tiempoInvertido;

    @Column(name = "adjunto_ruta", length = 255)
    private String adjuntoRuta;
    
    @Column(name = "involucrados", length = 255)
    private String involucrados;

    @Column(name = "fecha_compromiso")
    private LocalDate fechaCompromiso;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public IncidenciaSeguimiento() {
        this.fechaRegistro = LocalDateTime.now(); 
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdIncidencia() { return idIncidencia; }
    public void setIdIncidencia(Long idIncidencia) { this.idIncidencia = idIncidencia; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getNuevoEstado() { return nuevoEstado; }
    public void setNuevoEstado(String nuevoEstado) { this.nuevoEstado = nuevoEstado; }
    public String getTiempoInvertido() { return tiempoInvertido; }
    public void setTiempoInvertido(String tiempoInvertido) { this.tiempoInvertido = tiempoInvertido; }
    public String getAdjuntoRuta() { return adjuntoRuta; }
    public void setAdjuntoRuta(String adjuntoRuta) { this.adjuntoRuta = adjuntoRuta; }
    public String getInvolucrados() { return involucrados; }
    public void setInvolucrados(String involucrados) { this.involucrados = involucrados; }
    public LocalDate getFechaCompromiso() { return fechaCompromiso; }
    public void setFechaCompromiso(LocalDate fechaCompromiso) { this.fechaCompromiso = fechaCompromiso; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}