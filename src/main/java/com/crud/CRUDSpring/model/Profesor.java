package com.crud.CRUDSpring.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "profesor")
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProf;
	@Column
	private String nombreProf;
	@Column
	private String apellidoProf;
	@Column
	private int dniProf;
	@Column
	private Date fechaNacProf;
	@Column
	private int telefonoProf;
	@Column
	private String matriculaProf;

	// @OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "profesor")
	// private List<RegistroDeAsistencia> asistenciaProfesores = new
	// ArrayList<RegistroDeAsistencia>();

	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "profesor")
	private List<Asistencia> asistencias = new ArrayList<Asistencia>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "profesor_tiene_clase", joinColumns = { @JoinColumn(name = "id_prof") }, inverseJoinColumns = {
			@JoinColumn(name = "id_clase") })
	private List<Clase> clases = new ArrayList<Clase>();

	public Profesor() {
	}

	public Profesor(int idProf, String nombreProf, String apellidoProf, int dniProf, Date fechaNacProf,
			int telefonoProf, String matriculaProf, List<Asistencia> asistencias, List<Clase> clases) {
		this.idProf = idProf;
		this.nombreProf = nombreProf;
		this.apellidoProf = apellidoProf;
		this.dniProf = dniProf;
		this.fechaNacProf = fechaNacProf;
		this.telefonoProf = telefonoProf;
		this.matriculaProf = matriculaProf;
		this.asistencias = asistencias;
		this.clases = clases;
	}

	public int getIdProf() {
		return idProf;
	}

	public void setIdProf(int idProf) {
		this.idProf = idProf;
	}

	public String getNombreProf() {
		return nombreProf;
	}

	public void setNombreProf(String nombreProf) {
		this.nombreProf = nombreProf;
	}

	public String getApellidoProf() {
		return apellidoProf;
	}

	public void setApellidoProf(String apellidoProf) {
		this.apellidoProf = apellidoProf;
	}

	public int getDniProf() {
		return dniProf;
	}

	public void setDniProf(int dniProf) {
		this.dniProf = dniProf;
	}

	public Date getFechaNacProf() {
		return fechaNacProf;
	}

	public void setFechaNacProf(Date fechaNacProf) {
		this.fechaNacProf = fechaNacProf;
	}

	public int getTelefonoProf() {
		return telefonoProf;
	}

	public void setTelefonoProf(int telefonoProf) {
		this.telefonoProf = telefonoProf;
	}

	public String getMatriculaProf() {
		return matriculaProf;
	}

	public void setMatriculaProf(String matriculaProf) {
		this.matriculaProf = matriculaProf;
	}

	public List<Asistencia> getAsistenciass() {
		return asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	@Override
	public String toString() {
		return "Profesor [apellidoProf=" + apellidoProf + ",dniProf=" + dniProf + ", fechaNacProf=" + fechaNacProf
				+ ", idProf=" + idProf + " matriculaProf=" + matriculaProf + "]";
	}

	public String pdfToString() {
		return nombreProf + " " + apellidoProf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoProf == null) ? 0 : apellidoProf.hashCode());
		result = prime * result + ((asistencias == null) ? 0 : asistencias.hashCode());
		result = prime * result + ((clases == null) ? 0 : clases.hashCode());
		result = prime * result + dniProf;
		result = prime * result + ((fechaNacProf == null) ? 0 : fechaNacProf.hashCode());
		result = prime * result + idProf;
		result = prime * result + ((matriculaProf == null) ? 0 : matriculaProf.hashCode());
		result = prime * result + ((nombreProf == null) ? 0 : nombreProf.hashCode());
		result = prime * result + telefonoProf;
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
		Profesor other = (Profesor) obj;
		if (apellidoProf == null) {
			if (other.apellidoProf != null)
				return false;
		} else if (!apellidoProf.equals(other.apellidoProf))
			return false;
		if (asistencias == null) {
			if (other.asistencias != null)
				return false;
		} else if (!asistencias.equals(other.asistencias))
			return false;
		if (clases == null) {
			if (other.clases != null)
				return false;
		} else if (!clases.equals(other.clases))
			return false;
		if (dniProf != other.dniProf)
			return false;
		if (fechaNacProf == null) {
			if (other.fechaNacProf != null)
				return false;
		} else if (!fechaNacProf.equals(other.fechaNacProf))
			return false;
		if (idProf != other.idProf)
			return false;
		if (matriculaProf == null) {
			if (other.matriculaProf != null)
				return false;
		} else if (!matriculaProf.equals(other.matriculaProf))
			return false;
		if (nombreProf == null) {
			if (other.nombreProf != null)
				return false;
		} else if (!nombreProf.equals(other.nombreProf))
			return false;
		if (telefonoProf != other.telefonoProf)
			return false;
		return true;
	}

}
