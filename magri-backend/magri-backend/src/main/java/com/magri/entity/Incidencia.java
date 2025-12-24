package com.magri.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inc")
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "dsc_inc", nullable = false)
    private String dscInc;

    @Column(name = "cod_referencia_inc", nullable = false, length = 50, unique = true)
    private String codigoReferencia;

    @Column(name = "fec_registro_inc")
    private LocalDateTime fechaRegistro;

    @Column(name = "fec_cierre_inc")
    private LocalDateTime fechaCierre;

    @Column(name = "nvl_satis_inc")
    private Integer nivelSatisfaccion;

    @Column(name = "obs_inc")
    private String obsInc;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cat")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_pri")
    private Prioridad prioridad;

    @ManyToOne
    @JoinColumn(name = "id_est")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_responsable")
    private Usuario responsable;
    
    @ManyToOne
    @JoinColumn(name = "id_ar")
    private Area area;
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDscInc() {
        return dscInc;
    }

    public void setDscInc(String dscInc) {
        this.dscInc = dscInc;
    }

    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Integer getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setNivelSatisfaccion(Integer nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }
    public String getObsInc() { 
        return obsInc;
    }

    public void setObsInc(String obsInc) {
        this.obsInc = obsInc;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
}