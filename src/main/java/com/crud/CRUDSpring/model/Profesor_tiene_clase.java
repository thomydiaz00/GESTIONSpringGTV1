package com.crud.CRUDSpring.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profesor_tiene_clase")
public class Profesor_tiene_clase {
	public Profesor_tiene_clase() {
		}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idProfClase;
	//Varible idProf correspondiente a la class "Profesor".
	@Column
	private int idProf;
	//Varible idClase correspondiente a la class "Clase".
	@Column
	private int idClase;
	
	private Profesor_tiene_clase(int idProfClase, int idProf,int idClase) {
		super();
		this.idProfClase = idProfClase;
		this.idProf = idProf;
		this.idClase = idClase;
	}
	public int getIdProfClase() {
		return idProfClase;
	}

	public void setIdProfClase(int idProfClase) {
		this.idProfClase = idProfClase;
	}
	public int getIdProf() {
		return idProf;
	}
	public void setIdProf(int idProf) {
		this.idProf = idProf;
	}
	public int getIdClase() {
		return idClase;
	}
	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	
	
	
	
	
	
	
	
	
	
	
	}
	


