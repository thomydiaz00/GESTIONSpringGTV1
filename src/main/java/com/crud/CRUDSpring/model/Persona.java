package com.crud.CRUDSpring.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * clase para las personas(clientes), persisten los datos de la b.d
 */
@Entity
@Table(name = "persona")

public class Persona {
	public Persona() {
		
	}
	//estableced el id como si fuera una PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String telefono;
	private String equipo;
	private String serie;
	private String fechaEntrada;
	private String fechaSalida;
	public Persona(int id, String name, String telefono, String equipo, String serie, String fechaEntrada,
			String fechaSalida) {
		super();
		this.id = id;
		this.name = name;
		this.telefono = telefono;
		this.equipo = equipo;
		this.serie = serie;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	
	
	/*Hola, hago un comentario*/
	
	
	
	

}
