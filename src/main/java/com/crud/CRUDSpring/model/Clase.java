package com.crud.CRUDSpring.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@OneToMany(targetEntity=Horario.class,fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST},  mappedBy= "clase")
	private List<Horario> horarios = new ArrayList<Horario>();
	
	public void agregarHorario(Horario horario) {
		//asocia el horario con la clase
		//agrega el horario a la lista
		horarios.add(horario);
	}
	
	@OneToMany(targetEntity=Asistencia.class,cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Asistencia> asistencias = new ArrayList<Asistencia>();
	//profesor_tiene_clase N:N
	
	@JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER ,mappedBy =  "clases")
    private List<Profesor> profesores = new ArrayList<>();
	public Clase(int idClase, String deporte, String nombreDep, List<Horario> horarios, List<Asistencia> asistencias,
			List<Profesor> profesores) {
		super();
		this.idClase = idClase;
		this.deporte = deporte;
		this.nombreDep = nombreDep;
		this.horarios = horarios;
		this.asistencias = asistencias;
		this.profesores = profesores;
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
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}
	@Override
	public String toString() {
		return "Clase [idClase=" + idClase + ", deporte=" + deporte + ", nombreDep=" + nombreDep + ", horarios="
				+ horarios + ", asistencias=" + asistencias + ", profesores=" + profesores + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asistencias == null) ? 0 : asistencias.hashCode());
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
		if (asistencias == null) {
			if (other.asistencias != null)
				return false;
		} else if (!asistencias.equals(other.asistencias))
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
	
	
	

	
	
	
}