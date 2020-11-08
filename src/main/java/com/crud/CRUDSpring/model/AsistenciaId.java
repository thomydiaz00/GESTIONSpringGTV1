package com.crud.CRUDSpring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
@Embeddable
public class AsistenciaId implements Serializable {
	@Column(name = "id_profesor")
	private int idProf;
	@Column(name = "id_clase")
	private int idClase;
	
	
	public AsistenciaId() {
		
	}

	public AsistenciaId(int idProf, int idClase) {
		this.idProf = idProf;
		this.idClase = idClase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClase;
		result = prime * result + idProf;
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
		AsistenciaId other = (AsistenciaId) obj;
		if (idClase != other.idClase)
			return false;
		if (idProf != other.idProf)
			return false;
		return true;
	}

	

	
	
	
	
	

}
