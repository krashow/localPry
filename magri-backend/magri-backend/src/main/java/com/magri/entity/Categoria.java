package com.magri.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat")
    private Integer id;

    @Column(name = "tp_cat", nullable = false, length = 100)
    private String nombre;
    
    // ELIMINADO EL BLOQUE @OneToMany(mappedBy = "categoria")
    // private List<PreguntaIncidencia> preguntas;

    // Getters y setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    // ELIMINADOS GETTERS Y SETTERS para 'preguntas'
    /*
	public List<PreguntaIncidencia> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<PreguntaIncidencia> preguntas) {
		this.preguntas = preguntas;
	}
    */
}