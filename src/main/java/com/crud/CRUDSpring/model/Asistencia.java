package com.crud.CRUDSpring.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asistencia")
public class Asistencia {
	public Asistencia() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAsis;
	@Column
	private Date fechaAsis;
	@Column
	//Varible idProf correspondiente a la class "Profesor".
	private int idProf;	
	@Column
	//Varible idClase correspondiente a la class "Clase".
	private int idClase;	
	
	private Asistencia(int idAsis, Date fechaAsis, int idProf,int idClase) {
		super();
		this.idAsis = idAsis;
		this.fechaAsis = fechaAsis;
		this.idProf = idProf;
		this.idClase = idClase;
	}

	public int getIdAsis() {
		return idAsis;
	}

	public void setIdAsis(int idAsis) {
		this.idAsis = idAsis;
	}

	public Date getFechaAsis() {
		return fechaAsis;
	}

	public void setFechaAsis(Date fechaAsis) {
		this.fechaAsis = fechaAsis;
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
