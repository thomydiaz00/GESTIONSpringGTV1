package com.crud.CRUDSpring.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

import org.joda.time.Hours;
import org.springframework.format.annotation.DateTimeFormat;

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

	@ManyToOne
	@JoinColumn(name = "idDia")
	private DiaDePractica dia;

	@ManyToOne
	@JoinColumn(name = "idClase")
	private Clase clase;

	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "horario")
	private List<RegistroDeAsistencia> registrosDeAsistencias = new ArrayList<RegistroDeAsistencia>();

	public Horario() {
	}

	@Override
	public String toString() {
		return "Horario [  hora_fin=" + hora_fin + ", hora_inicio=" + hora_inicio + ", idHorario=" + idHorario
				+ ", lugar=" + lugar + "]";
	}

	public Horario(int idHorario, String hora_inicio, String hora_fin, String lugar, DiaDePractica dia,
			List<RegistroDeAsistencia> registrosDeAsistencias, Clase clase) {
		this.idHorario = idHorario;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.lugar = lugar;
		this.dia = dia;
		this.registrosDeAsistencias = registrosDeAsistencias;
		this.clase = clase;
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

	public DiaDePractica getDia() {
		return dia;
	}

	public void setDia(DiaDePractica dia) {
		this.dia = dia;
	}

	public List<RegistroDeAsistencia> getRegistrosDeRegistroDeAsistencias() {
		return registrosDeAsistencias;
	}

	public void setRegistrosDeAsistencias(List<RegistroDeAsistencia> registrosDeAsistencias) {
		this.registrosDeAsistencias = registrosDeAsistencias;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrosDeAsistencias == null) ? 0 : registrosDeAsistencias.hashCode());
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
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
		if (registrosDeAsistencias == null) {
			if (other.registrosDeAsistencias != null)
				return false;
		} else if (!registrosDeAsistencias.equals(other.registrosDeAsistencias))
			return false;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
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