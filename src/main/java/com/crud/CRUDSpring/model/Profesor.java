package com.crud.CRUDSpring.model;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {
	public Profesor() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
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
	
	private Profesor(int idProf, String nombreProf, String apellidoProf, int dniProf, String direccionProf, String telefonoProf, String institucionProf, Date fechaNacProf, String matriculaProf) {
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
	}
	

	
	
	
	