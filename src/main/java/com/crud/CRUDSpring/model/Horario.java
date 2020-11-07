package com.crud.CRUDSpring.model;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {
	public Horario() {
		}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idHorario;
	@Column
	private Time hora_inicio;
	@Column
	private Time hora_fin;
	@Column
	private String dia_semana;
	//Varible idClase correspondiente a la class "Clase".
	@Column
	private int idClase;
	
	
	private Horario(int idHorario, Time hora_inicio, Time hora_fin,String dia_semana ) {
		super();
		this.idHorario = idHorario;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
	}


	public int getIdHorario() {
		return idHorario;
	}


	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}


	public Time getHora_inicio() {
		return hora_inicio;
	}


	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}


	public Time getHora_fin() {
		return hora_fin;
	}


	public void setHora_fin(Time hora_fin) {
		this.hora_fin = hora_fin;
	}


	public String getDia_semana() {
		return dia_semana;
	}


	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}


	public int getIdClase() {
		return idClase;
	}


	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}
	
	
}