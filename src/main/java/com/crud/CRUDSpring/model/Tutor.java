package com.crud.CRUDSpring.model;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tutor")
public class Tutor {
	public Tutor() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idTutor;
	@Column 
	private String nombreTutor;
	@Column 
	private String apellidoTutor;
	@Column
	private int dniTutor;
	@Column
	private String direccionTutor;
	@Column 
	private String telefonoTutor;
	//alumno
	@OneToMany
	@JoinColumn (name = "idAlumno")
	private Set<Alumno> alumnos = new HashSet<Alumno>();
	
	
	
	public Tutor(int idTutor, String nombreTutor, String apellidoTutor, int dniTutor, String direccionTutor,
			String telefonoTutor) {
		super();
		this.idTutor = idTutor;
		this.nombreTutor = nombreTutor;
		this.apellidoTutor = apellidoTutor;
		this.dniTutor = dniTutor;
		this.direccionTutor = direccionTutor;
		this.telefonoTutor = telefonoTutor;
		
	}

	public int getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getApellidoTutor() {
		return apellidoTutor;
	}

	public void setApellidoTutor(String apellidoTutor) {
		this.apellidoTutor = apellidoTutor;
	}

	public int getDniTutor() {
		return dniTutor;
	}

	public void setDniTutor(int dniTutor) {
		this.dniTutor = dniTutor;
	}

	public String getDireccionTutor() {
		return direccionTutor;
	}

	public void setDireccionTutor(String direccionTutor) {
		this.direccionTutor = direccionTutor;
	}

	public String getTelefonoTutor() {
		return telefonoTutor;
	}

	public void setTelefonoTutor(String telefonoTutor) {
		this.telefonoTutor = telefonoTutor;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoTutor == null) ? 0 : apellidoTutor.hashCode());
		result = prime * result + ((direccionTutor == null) ? 0 : direccionTutor.hashCode());
		result = prime * result + dniTutor;
		result = prime * result + idTutor;
		result = prime * result + ((nombreTutor == null) ? 0 : nombreTutor.hashCode());
		result = prime * result + ((telefonoTutor == null) ? 0 : telefonoTutor.hashCode());
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
		Tutor other = (Tutor) obj;
		
		if (apellidoTutor == null) {
			if (other.apellidoTutor != null)
				return false;
		} else if (!apellidoTutor.equals(other.apellidoTutor))
			return false;
		if (direccionTutor == null) {
			if (other.direccionTutor != null)
				return false;
		} else if (!direccionTutor.equals(other.direccionTutor))
			return false;
		if (dniTutor != other.dniTutor)
			return false;
		if (idTutor != other.idTutor)
			return false;
		if (nombreTutor == null) {
			if (other.nombreTutor != null)
				return false;
		} else if (!nombreTutor.equals(other.nombreTutor))
			return false;
		if (telefonoTutor == null) {
			if (other.telefonoTutor != null)
				return false;
		} else if (!telefonoTutor.equals(other.telefonoTutor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tutor [idTutor=" + idTutor + ", nombreTutor=" + nombreTutor + ", apellidoTutor=" + apellidoTutor
				+ ", dniTutor=" + dniTutor + ", direccionTutor=" + direccionTutor + ", telefonoTutor=" + telefonoTutor
				;
	}
	
	
	
	
	
}