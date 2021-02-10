package com.crud.CRUDSpring.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

@Entity
@Table(name = "horario")
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idHorario;
	@Column
	private String hora_inicio;
	@Column
	private String hora_fin;
	@Column
	private String lugar;
	@Column
	private String dia_semana;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "horario_dia", joinColumns = { @JoinColumn(name = "id_horario") }, inverseJoinColumns = {
			@JoinColumn(name = "id_dia") })
	private List<DiaDePractica> dias = new ArrayList<DiaDePractica>();
	@ManyToOne
	@JoinColumn(name = "idClase")
	private Clase clase;

	public Horario(int idHorario, String hora_inicio, String hora_fin, String lugar, String dia_semana,
			List<DiaDePractica> dias, Clase clase) {
		this.idHorario = idHorario;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.lugar = lugar;
		this.dia_semana = dia_semana;
		this.dias = dias;
		this.clase = clase;
	}

	public Horario() {
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public List<DiaDePractica> getDias() {
		return dias;
	}

	public void setDias(List<DiaDePractica> dias) {
		this.dias = dias;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@Override
	public String toString() {
		return "Horario [clase=" + clase + ", dia_semana=" + dia_semana + ", hora_fin=" + hora_fin + ", hora_inicio="
				+ hora_inicio + ", idHorario=" + idHorario + ", lugar=" + lugar + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + ((dia_semana == null) ? 0 : dia_semana.hashCode());
		result = prime * result + ((hora_fin == null) ? 0 : hora_fin.hashCode());
		result = prime * result + ((hora_inicio == null) ? 0 : hora_inicio.hashCode());
		result = prime * result + idHorario;
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
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
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
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
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		return true;
	}

}