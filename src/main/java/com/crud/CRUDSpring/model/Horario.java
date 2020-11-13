package com.crud.CRUDSpring.model;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	public Horario(int idHorario, Time hora_inicio, Time hora_fin, String dia_semana) {
		super();
		this.idHorario = idHorario;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.dia_semana = dia_semana;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia_semana == null) ? 0 : dia_semana.hashCode());
		result = prime * result + ((hora_fin == null) ? 0 : hora_fin.hashCode());
		result = prime * result + ((hora_inicio == null) ? 0 : hora_inicio.hashCode());
		result = prime * result + idHorario;
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
		Horario other = (Horario) obj;
		if (dia_semana == null) {
			if (other.dia_semana != null)
				return false;
		} else if (!dia_semana.equals(other.dia_semana))
			return false;
		if (hora_fin == null) {
			if (other.hora_fin != null)
				return false;
		} else if (!hora_fin.equals(other.hora_fin))
			return false;
		if (hora_inicio == null) {
			if (other.hora_inicio != null)
				return false;
		} else if (!hora_inicio.equals(other.hora_inicio))
			return false;
		if (idHorario != other.idHorario)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Horario [idHorario=" + idHorario + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin
				+ ", dia_semana=" + dia_semana + "]";
	}
	
	
}