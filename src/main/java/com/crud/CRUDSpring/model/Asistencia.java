package com.crud.CRUDSpring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private int idAsistencia;

	@ManyToOne
	@JoinColumn(name = "idClase")
	private Clase clase;

	@Column
	private LocalDate fechaAsistencia;
	@Column
	private boolean estadoAsistencia;

	public Asistencia(int idAsistencia, Profesor profesor, Clase clase, Horario horario, LocalDate fechaAsistencia,
			boolean estadoAsistencia) {
		this.idAsistencia = idAsistencia;
		this.fechaAsistencia = fechaAsistencia;
		this.estadoAsistencia = estadoAsistencia;
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

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estadoAsistencia ? 1231 : 1237);
		result = prime * result + ((fechaAsistencia == null) ? 0 : fechaAsistencia.hashCode());
		result = prime * result + idAsistencia;
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
		if (estadoAsistencia != other.estadoAsistencia)
			return false;
		if (fechaAsistencia == null) {
			if (other.fechaAsistencia != null)
				return false;
		} else if (!fechaAsistencia.equals(other.fechaAsistencia))
			return false;
		if (idAsistencia != other.idAsistencia)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asistencia [clase=" + clase + ", estadoAsistencia=" + estadoAsistencia + ", fechaAsistencia="
				+ fechaAsistencia + ", idAsistencia=" + idAsistencia + "]";
	}

}
