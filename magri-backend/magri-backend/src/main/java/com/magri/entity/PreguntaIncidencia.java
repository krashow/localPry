package com.magri.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "preguntas_incidencia")
public class PreguntaIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cat", nullable = false)
    private Categoria categoria;

    private String pregunta;

    private String tipoCampo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }

    public String getTipoCampo() { return tipoCampo; }
    public void setTipoCampo(String tipoCampo) { this.tipoCampo = tipoCampo; }
}
