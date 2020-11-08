package com.crud.CRUDSpring.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	
	
	
	
	@OneToMany(mappedBy = "clase")
	private Set<Asistencia> asistenciaClases = new HashSet<Asistencia>();
	//profesor_tiene_clase
	@ManyToMany(mappedBy = "clases")
	private Set<Profesor> profesores = new HashSet<Profesor>();
	
	private Clase(int idClase, String deporte, String nombreDep) {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deporte == null) ? 0 : deporte.hashCode());
		result = prime * result + idClase;
		result = prime * result + ((nombreDep == null) ? 0 : nombreDep.hashCode());
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
		Clase other = (Clase) obj;
		if (deporte == null) {
			if (other.deporte != null)
				return false;
		} else if (!deporte.equals(other.deporte))
			return false;
		if (idClase != other.idClase)
			return false;
		if (nombreDep == null) {
			if (other.nombreDep != null)
				return false;
		} else if (!nombreDep.equals(other.nombreDep))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", deporte=" + deporte + ", nombreDep=" + nombreDep + "]";
	}
	
	
	
}