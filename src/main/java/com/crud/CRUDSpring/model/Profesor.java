package com.crud.CRUDSpring.model;
import java.sql.Date;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private String direccionProf;
	@Column 
	private String telefonoProf;
	@Column
	private String institucionProf;
	@Column
	private Date fechaNacProf;
	@Column
	private String matriculaProf;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "clase")
	private List<Asistencia> asistenciaProfesores = new ArrayList<Asistencia>();
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable (name= "profesor_tiene_clase",
			joinColumns= {@JoinColumn (name="id_prof")},
			inverseJoinColumns= {@JoinColumn (name="id_clase")}
	)
	private List<Clase> clases = new ArrayList<Clase>();

	public Profesor() {
	}

	public Profesor(int idProf, String nombreProf, String apellidoProf, int dniProf, String direccionProf,
			String telefonoProf, String institucionProf, Date fechaNacProf, String matriculaProf,
			List<Asistencia> asistenciaProfesores, List<Clase> clases) {
		super();
		this.idProf = idProf;
		this.nombreProf = nombreProf;
		this.apellidoProf = apellidoProf;
		this.dniProf = dniProf;
		this.direccionProf = direccionProf;
		this.telefonoProf = telefonoProf;
		this.institucionProf = institucionProf;
		this.fechaNacProf = fechaNacProf;
		this.matriculaProf = matriculaProf;
		this.asistenciaProfesores = asistenciaProfesores;
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

	public String getDireccionProf() {
		return direccionProf;
	}

	public void setDireccionProf(String direccionProf) {
		this.direccionProf = direccionProf;
	}

	public String getTelefonoProf() {
		return telefonoProf;
	}

	public void setTelefonoProf(String telefonoProf) {
		this.telefonoProf = telefonoProf;
	}

	public String getInstitucionProf() {
		return institucionProf;
	}

	public void setInstitucionProf(String institucionProf) {
		this.institucionProf = institucionProf;
	}

	public Date getFechaNacProf() {
		return fechaNacProf;
	}

	public void setFechaNacProf(Date fechaNacProf) {
		this.fechaNacProf = fechaNacProf;
	}

	public String getMatriculaProf() {
		return matriculaProf;
	}

	public void setMatriculaProf(String matriculaProf) {
		this.matriculaProf = matriculaProf;
	}

	public List<Asistencia> getAsistenciaProfesores() {
		return asistenciaProfesores;
	}

	public void setAsistenciaProfesores(List<Asistencia> asistenciaProfesores) {
		this.asistenciaProfesores = asistenciaProfesores;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoProf == null) ? 0 : apellidoProf.hashCode());
		result = prime * result + ((asistenciaProfesores == null) ? 0 : asistenciaProfesores.hashCode());
		result = prime * result + ((clases == null) ? 0 : clases.hashCode());
		result = prime * result + ((direccionProf == null) ? 0 : direccionProf.hashCode());
		result = prime * result + dniProf;
		result = prime * result + ((fechaNacProf == null) ? 0 : fechaNacProf.hashCode());
		result = prime * result + idProf;
		result = prime * result + ((institucionProf == null) ? 0 : institucionProf.hashCode());
		result = prime * result + ((matriculaProf == null) ? 0 : matriculaProf.hashCode());
		result = prime * result + ((nombreProf == null) ? 0 : nombreProf.hashCode());
		result = prime * result + ((telefonoProf == null) ? 0 : telefonoProf.hashCode());
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
		if (asistenciaProfesores == null) {
			if (other.asistenciaProfesores != null)
				return false;
		} else if (!asistenciaProfesores.equals(other.asistenciaProfesores))
			return false;
		if (clases == null) {
			if (other.clases != null)
				return false;
		} else if (!clases.equals(other.clases))
			return false;
		if (direccionProf == null) {
			if (other.direccionProf != null)
				return false;
		} else if (!direccionProf.equals(other.direccionProf))
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
		if (institucionProf == null) {
			if (other.institucionProf != null)
				return false;
		} else if (!institucionProf.equals(other.institucionProf))
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
		if (telefonoProf == null) {
			if (other.telefonoProf != null)
				return false;
		} else if (!telefonoProf.equals(other.telefonoProf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profesor [idProf=" + idProf + ", nombreProf=" + nombreProf + ", apellidoProf=" + apellidoProf
				+ ", dniProf=" + dniProf + ", direccionProf=" + direccionProf + ", telefonoProf=" + telefonoProf
				+ ", institucionProf=" + institucionProf + ", fechaNacProf=" + fechaNacProf + ", matriculaProf="
				+ matriculaProf +  "]";
	}
	
	
	
	
	
	
}
	
	
	