package com.crud.CRUDSpring.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


/* 
 * Esta tabla va a representar una relacion n:m
 * entre Profesor y clase. La declaramos como una
 * clase porque deben agregarse columnas adicionales
 */
@Entity
@Table(name="asistencia")
public class Asistencia {
	public Asistencia() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAsistencia;
	
	@ManyToOne
	@JoinColumn(name= "idProf")
	private Profesor profesor;
	
	@ManyToOne
	@JoinColumn(name="idClase")
	private Clase clase;
	
	@Column
	private Date fechaAsistencia;
	@Column
	private char estadoAsistencia;
	
	
	
	
	public Asistencia(Profesor profesor, Clase clase, Date fechaAsistencia, char estadoAsistencia) {
		this.profesor = profesor;
		this.clase = clase;
		this.fechaAsistencia = fechaAsistencia;
		this.estadoAsistencia = estadoAsistencia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + estadoAsistencia;
		result = prime * result + ((fechaAsistencia == null) ? 0 : fechaAsistencia.hashCode());
		result = prime * result + idAsistencia;
		result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Asistencia other = (Asistencia) obj;
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
		if (estadoAsistencia != other.estadoAsistencia)
			return false;
		if (fechaAsistencia == null) {
			if (other.fechaAsistencia != null)
				return false;
		} else if (!fechaAsistencia.equals(other.fechaAsistencia))
			return false;
		if (idAsistencia != other.idAsistencia)
			return false;
		if (profesor == null) {
			if (other.profesor != null)
				return false;
		} else if (!profesor.equals(other.profesor))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Asistencia [idAsistencia=" + idAsistencia + ", profesor=" + profesor + ", clase=" + clase
				+ ", fechaAsistencia=" + fechaAsistencia + ", estadoAsistencia=" + estadoAsistencia + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
