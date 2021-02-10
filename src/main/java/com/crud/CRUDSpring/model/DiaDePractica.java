package com.crud.CRUDSpring.model;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DiaDePractica")
public class DiaDePractica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idDia;
    @Column
    private String diaDeLaSemana;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "dias")
    private List<Horario> horarios = new ArrayList<>();

    public DiaDePractica() {
    }

    public DiaDePractica(int idDia, String diaDeLaSemana, List<Horario> horarios) {
        this.idDia = idDia;
        this.diaDeLaSemana = diaDeLaSemana;
        this.horarios = horarios;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public String getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public void setDiaDeLaSemana(String diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "DiaDePractica [diaDeLaSemana=" + diaDeLaSemana + ", idDia=" + idDia + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((diaDeLaSemana == null) ? 0 : diaDeLaSemana.hashCode());
        result = prime * result + idDia;
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
        DiaDePractica other = (DiaDePractica) obj;
        if (diaDeLaSemana == null) {
            if (other.diaDeLaSemana != null)
                return false;
        } else if (!diaDeLaSemana.equals(other.diaDeLaSemana))
            return false;
        if (idDia != other.idDia)
            return false;
        return true;
    }

}
