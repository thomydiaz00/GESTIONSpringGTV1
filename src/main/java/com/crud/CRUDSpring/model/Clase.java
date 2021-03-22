package com.crud.CRUDSpring.model;

import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaInicio;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaFin;

	// @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy =
	// "clase")
	// private List<Horario> horarios = new ArrayList<Horario>();

	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "clase")
	private List<Asistencia> asistencias = new ArrayList<Asistencia>();
	// profesor_tiene_clase N:N

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "clases")
	private List<Profesor> profesores = new ArrayList<Profesor>();

	@ManyToMany()
	@JoinTable(name = "clase_has_dias", joinColumns = { @JoinColumn(name = "id_clase") }, inverseJoinColumns = {
			@JoinColumn(name = "id_dia") })
	private List<DiaDePractica> dias = new ArrayList<DiaDePractica>();

	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "clase", orphanRemoval = true)
	private List<Horario> horarios = new ArrayList<Horario>();

	@Override
	public String toString() {
		return "Clase [deporte=" + ", idClase=" + idClase + ", nombreDep=" + nombreDep + "]";
	}

	public Clase(int idClase, String deporte, String nombreDep, LocalDate fechaInicio, LocalDate fechaFin,
			List<Profesor> profesores, List<DiaDePractica> dias, List<Horario> horarios, List<Asistencia> asistencias) {
		this.idClase = idClase;
		this.deporte = deporte;
		this.nombreDep = nombreDep;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.profesores = profesores;
		this.dias = dias;
		this.horarios = horarios;
		this.asistencias = asistencias;
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	public List<DiaDePractica> getDias() {
		return dias;
	}

	public void setDias(List<DiaDePractica> dias) {
		this.dias = dias;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deporte == null) ? 0 : deporte.hashCode());
		result = prime * result + ((dias == null) ? 0 : dias.hashCode());
		result = prime * result + ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result + ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
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
		if (deporte == null) {
			if (other.deporte != null)
				return false;
		} else if (!deporte.equals(other.deporte))
			return false;
		if (dias == null) {
			if (other.dias != null)
				return false;
		} else if (!dias.equals(other.dias))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
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