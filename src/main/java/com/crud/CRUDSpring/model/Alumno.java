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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {
	public Alumno() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlumno;
	
	
	@Column 
	private String nombreAlumno;
	@Column 
	private String apellidoAlumno;
	@Column
	private int dniAlumno;
	@Column
	private String direccionAlumno;
	@Column 
	private String telefonoAlumno;
	@Column
	private Date fechaNacAlumno;
	
	//alumno_tiene_clase N:N
	@ManyToMany 
	@JoinTable (name= "alumno_tiene_clase",
			joinColumns= {@JoinColumn (name="idAlumno")},
			inverseJoinColumns= {@JoinColumn (name="idClase")}
	)
	private Set<Clase> clasesAlumno = new HashSet<Clase>();
	//categoria 1:1
	@OneToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	//tutor 1:N
	@ManyToOne(fetch = FetchType.LAZY)
	private Tutor tutor;
	
	
	
	
	public Alumno(int idAlumno, String nombreAlumno, String apellidoAlumno, int dniAlumno, String direccionAlumno,
			String telefonoAlumno, Date fechaNacAlumno) {
	
		this.idAlumno = idAlumno;
		this.nombreAlumno = nombreAlumno;
		this.apellidoAlumno = apellidoAlumno;
		this.dniAlumno = dniAlumno;
		this.direccionAlumno = direccionAlumno;
		this.telefonoAlumno = telefonoAlumno;
		this.fechaNacAlumno = fechaNacAlumno;
	}
	
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	public String getApellidoAlumno() {
		return apellidoAlumno;
	}
	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno;
	}
	public int getDniAlumno() {
		return dniAlumno;
	}
	public void setDniAlumno(int dniAlumno) {
		this.dniAlumno = dniAlumno;
	}
	public String getDireccionAlumno() {
		return direccionAlumno;
	}
	public void setDireccionAlumno(String direccionAlumno) {
		this.direccionAlumno = direccionAlumno;
	}
	public String getTelefonoAlumno() {
		return telefonoAlumno;
	}
	public void setTelefonoAlumno(String telefonoAlumno) {
		this.telefonoAlumno = telefonoAlumno;
	}
	public Date getFechaNacAlumno() {
		return fechaNacAlumno;
	}
	public void setFechaNacAlumno(Date fechaNacAlumno) {
		this.fechaNacAlumno = fechaNacAlumno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoAlumno == null) ? 0 : apellidoAlumno.hashCode());
		result = prime * result + ((direccionAlumno == null) ? 0 : direccionAlumno.hashCode());
		result = prime * result + dniAlumno;
		result = prime * result + ((fechaNacAlumno == null) ? 0 : fechaNacAlumno.hashCode());
		result = prime * result + idAlumno;
		result = prime * result + ((nombreAlumno == null) ? 0 : nombreAlumno.hashCode());
		result = prime * result + ((telefonoAlumno == null) ? 0 : telefonoAlumno.hashCode());
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
		Alumno other = (Alumno) obj;
		if (apellidoAlumno == null) {
			if (other.apellidoAlumno != null)
				return false;
		} else if (!apellidoAlumno.equals(other.apellidoAlumno))
			return false;
		if (direccionAlumno == null) {
			if (other.direccionAlumno != null)
				return false;
		} else if (!direccionAlumno.equals(other.direccionAlumno))
			return false;
		if (dniAlumno != other.dniAlumno)
			return false;
		if (fechaNacAlumno == null) {
			if (other.fechaNacAlumno != null)
				return false;
		} else if (!fechaNacAlumno.equals(other.fechaNacAlumno))
			return false;
		if (idAlumno != other.idAlumno)
			return false;
		if (nombreAlumno == null) {
			if (other.nombreAlumno != null)
				return false;
		} else if (!nombreAlumno.equals(other.nombreAlumno))
			return false;
		if (telefonoAlumno == null) {
			if (other.telefonoAlumno != null)
				return false;
		} else if (!telefonoAlumno.equals(other.telefonoAlumno))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Alumno [idAlumno=" + idAlumno + ", nombreAlumno=" + nombreAlumno + ", apellidoAlumno=" + apellidoAlumno
				+ ", dniAlumno=" + dniAlumno + ", direccionAlumno=" + direccionAlumno + ", telefonoAlumno="
				+ telefonoAlumno + ", fechaNacAlumno=" + fechaNacAlumno + "]";
	}
	
	
	
	
	
	
}