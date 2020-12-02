package com.crud.CRUDSpring.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	//asistencia_clase 1:N
	@OneToMany(mappedBy = "clase")
	private Set<Asistencia> asistenciaClases = new HashSet<Asistencia>();
	//profesor_tiene_clase N:N
	
	@JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER ,mappedBy =  "clases")
    private List<Profesor> profesores = new ArrayList<>();

	
	@OneToMany
	private List<Horario> horarios = new ArrayList<Horario>();


	public Clase(int idClase, String deporte, String nombreDep, Set<Asistencia> asistenciaClases,
			List<Profesor> profesores, List<Horario> horarios) {
		super();
		this.idClase = idClase;
		this.deporte = deporte;
		this.nombreDep = nombreDep;
		this.asistenciaClases = asistenciaClases;
		this.profesores = profesores;
		this.horarios = horarios;
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


	public Set<Asistencia> getAsistenciaClases() {
		return asistenciaClases;
	}


	public void setAsistenciaClases(Set<Asistencia> asistenciaClases) {
		this.asistenciaClases = asistenciaClases;
	}


	public List<Profesor> getProfesores() {
		return profesores;
	}


	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}


	public List<Horario> getHorarios() {
		return horarios;
	}


	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asistenciaClases == null) ? 0 : asistenciaClases.hashCode());
		result = prime * result + ((deporte == null) ? 0 : deporte.hashCode());
		result = prime * result + ((horarios == null) ? 0 : horarios.hashCode());
		result = prime * result + idClase;
		result = prime * result + ((nombreDep == null) ? 0 : nombreDep.hashCode());
		result = prime * result + ((profesores == null) ? 0 : profesores.hashCode());
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
		if (asistenciaClases == null) {
			if (other.asistenciaClases != null)
				return false;
		} else if (!asistenciaClases.equals(other.asistenciaClases))
			return false;
		if (deporte == null) {
			if (other.deporte != null)
				return false;
		} else if (!deporte.equals(other.deporte))
			return false;
		if (horarios == null) {
			if (other.horarios != null)
				return false;
		} else if (!horarios.equals(other.horarios))
			return false;
		if (idClase != other.idClase)
			return false;
		if (nombreDep == null) {
			if (other.nombreDep != null)
				return false;
		} else if (!nombreDep.equals(other.nombreDep))
			return false;
		if (profesores == null) {
			if (other.profesores != null)
				return false;
		} else if (!profesores.equals(other.profesores))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", deporte=" + deporte + ", nombreDep=" + nombreDep + ", asistenciaClases="
				+ asistenciaClases + ", profesores=" + profesores + ", horarios=" + horarios + "]";
	}
	
	
	
}