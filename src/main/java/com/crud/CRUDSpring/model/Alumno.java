package com.crud.CRUDSpring.model;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {
	public Alumno() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAlumno;
	@Column 
	private String nombreAlu;
	@Column 
	private String apellidoAlu;
	@Column
	private int dniAlu;
	@Column
	private String direccionAlu;
	@Column 
	private String telefonoAlu;
	@Column
	private Date fechaNacAlu;
	//Varible idTutor correspondiente a la class "Tutor".
	@Column
	private int idTutor;
	
	
	private Alumno(int idAlumno, String nombreAlu, String apellidoAlu, int dniAlu, String direccionAlu, String telefonoAlu, Date fechaNacAlu, int idTutor) {
		super();
		this.idAlumno = idAlumno;
		this.nombreAlu = nombreAlu;
		this.apellidoAlu = apellidoAlu;
		this.dniAlu = dniAlu;
		this.direccionAlu = direccionAlu;
		this.telefonoAlu = telefonoAlu;
		this.fechaNacAlu = fechaNacAlu;
		this.idTutor = idTutor;
	}


	public int getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}


	public String getNombreAlu() {
		return nombreAlu;
	}


	public void setNombreAlu(String nombreAlu) {
		this.nombreAlu = nombreAlu;
	}


	public String getApellidoAlu() {
		return apellidoAlu;
	}


	public void setApellidoAlu(String apellidoAlu) {
		this.apellidoAlu = apellidoAlu;
	}


	public int getDniAlu() {
		return dniAlu;
	}


	public void setDniAlu(int dniAlu) {
		this.dniAlu = dniAlu;
	}


	public String getDireccionAlu() {
		return direccionAlu;
	}


	public void setDireccionAlu(String direccionAlu) {
		this.direccionAlu = direccionAlu;
	}


	public String getTelefonoAlu() {
		return telefonoAlu;
	}


	public void setTelefonoAlu(String telefonoAlu) {
		this.telefonoAlu = telefonoAlu;
	}


	public Date getFechaNacAlu() {
		return fechaNacAlu;
	}


	public void setFechaNacAlu(Date fechaNacAlu) {
		this.fechaNacAlu = fechaNacAlu;
	}


	public int getIdTutor() {
		return idTutor;
	}


	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
	}
	
	
	
	
}