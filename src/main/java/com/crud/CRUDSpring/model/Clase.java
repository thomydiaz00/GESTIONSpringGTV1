package com.crud.CRUDSpring.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clase")
public class Clase {
	public Clase() {
		}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idClase;
	@Column
	private String deporte;
	@Column
	private String nombreDep;
	
	
	private Clase(int idClase, String deporte, String nombreDep) {
		super();
		this.idClase = idClase;
		this.nombreDep = nombreDep;
		this.deporte = deporte;
	}


	public int getIdClase() {
		return idClase;
	}


	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}


	public String getDeporte() {
		return deporte;
	}


	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}


	public String getNombreDep() {
		return nombreDep;
	}


	public void setNombreDep(String nombreDep) {
		this.nombreDep = nombreDep;
	}
	
}