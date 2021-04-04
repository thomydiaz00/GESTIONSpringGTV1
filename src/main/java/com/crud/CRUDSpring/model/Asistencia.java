package com.crud.CRUDSpring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/* 
 * Esta tabla va a representar una relacion n:m
 * entre Profesor y clase. La declaramos como una
 * clase porque deben agregarse columnas adicionales
 */
@Entity
@Table(name = "asistencia")
public class Asistencia {
	public Asistencia() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAsistencia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProf")
	private Profesor profesor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idClase")
	private Clase clase;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "idRegistro.asistencia")
	private List<RegistroDeAsistencia> registrosDeAsistencia;
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumns({ @JoinColumn(name = "idProfesor"), @JoinColumn(name =
	// "idClase") })
	// private Clase clase;
	@Column
	private LocalDate fechaAsistencia;
	@Column
	private boolean estadoAsistencia;

	public Asistencia(int idAsistencia, Profesor profesor, Clase clase, LocalDate fechaAsistencia,
			boolean estadoAsistencia, List<RegistroDeAsistencia> registrosDeAsistencias) {
		this.profesor = profesor;
		this.clase = clase;
		this.fechaAsistencia = fechaAsistencia;
		this.estadoAsistencia = estadoAsistencia;
		this.idAsistencia = idAsistencia;
		this.registrosDeAsistencia = registrosDeAsistencias;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public int getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public LocalDate getFechaAsistencia() {
		return fechaAsistencia;
	}

	public void setFechaAsistencia(LocalDate fechaAsistencia) {
		this.fechaAsistencia = fechaAsistencia;
	}

	public boolean isEstadoAsistencia() {
		return estadoAsistencia;
	}

	public void setEstadoAsistencia(boolean estadoAsistencia) {
		this.estadoAsistencia = estadoAsistencia;
	}

	public List<RegistroDeAsistencia> getRegistrosDeAsistencia() {
		return registrosDeAsistencia;
	}

	public void setRegistrosDeAsistencia(List<RegistroDeAsistencia> registrosDeAsistencia) {
		this.registrosDeAsistencia = registrosDeAsistencia;
	}

	@Override
	public String toString() {
		return "Asistencia [clase=" + clase + ", estadoAsistencia=" + estadoAsistencia + ", fechaAsistencia="
				+ fechaAsistencia + ", idAsistencia=" + idAsistencia + ", profesor=" + profesor
				+ ", registrosDeAsistencia=" + registrosDeAsistencia.size() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + (estadoAsistencia ? 1231 : 1237);
		result = prime * result + ((fechaAsistencia == null) ? 0 : fechaAsistencia.hashCode());
		result = prime * result + idAsistencia;
		result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
		result = prime * result + ((registrosDeAsistencia == null) ? 0 : registrosDeAsistencia.hashCode());
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
		if (registrosDeAsistencia == null) {
			if (other.registrosDeAsistencia != null)
				return false;
		} else if (!registrosDeAsistencia.equals(other.registrosDeAsistencia))
			return false;
		return true;
	}

}
