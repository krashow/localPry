package com.magri.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prioridad")
public class Prioridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pri")
    private Integer id;

    @Column(name = "tp_pri", nullable = false, length = 50)
    private String nombre;

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
}
