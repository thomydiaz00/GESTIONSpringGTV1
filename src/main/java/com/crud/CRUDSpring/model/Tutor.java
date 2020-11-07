package com.crud.CRUDSpring.model;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	//por ahi no interesaria tanto saber cuando nacio el tutor, se puede sacar. ( sacar del constructor tambien en ese caso)
	@Column
	private Date fechaNacTutor;
	
	
	
	private Tutor(int idTutor, String nombreTutor, String apellidoTutor, int dniTutor, String direccionTutor, String telefonoTutor, Date fechaNacTutor) {
		super();
		this.idTutor = idTutor;
		this.nombreTutor = nombreTutor;
		this.apellidoTutor = apellidoTutor;
		this.dniTutor = dniTutor;
		this.direccionTutor = direccionTutor;
		this.telefonoTutor = telefonoTutor;
		this.fechaNacTutor = fechaNacTutor;
		this.idTutor = idTutor;
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



	public Date getFechaNacTutor() {
		return fechaNacTutor;
	}



	public void setFechaNacTutor(Date fechaNacTutor) {
		this.fechaNacTutor = fechaNacTutor;
	}
	
	
	
	
	
	
	
	
}