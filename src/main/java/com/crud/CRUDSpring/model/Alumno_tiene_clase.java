package com.crud.CRUDSpring.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alumno_tiene_clase")
public class Alumno_tiene_clase {
	public Alumno_tiene_clase() {
		}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idAluClase;
	//Varible idAlumno correspondiente a la class "Alumno".
	@Column
	private int idAlumno;
	//Varible idClase correspondiente a la class "Clase".
	@Column
	private int idClase;
	
	
	private Alumno_tiene_clase(int idAluClase, int idAlumno,int idClase) {
		super();
		this.idAluClase = idAluClase;
		this.idAlumno = idAlumno;
		this.idClase = idClase;
	}
	
	public int getIdAluClase() {
		return idAluClase;
	}
	public void setIdAluClase(int idAluClase) {
		this.idAluClase = idAluClase;
	}
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	

	
	


}