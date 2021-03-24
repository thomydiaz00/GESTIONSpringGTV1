package com.crud.CRUDSpring.model;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
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

@Entity
@Table(name = "DiaDePractica")
public class DiaDePractica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idDia;
    @Column
    private String diaDeLaSemana;

    @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroDeAsistencia> registroDeAsistencias = new ArrayList<RegistroDeAsistencia>();
    
    @OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "dia", orphanRemoval = true)
    private List<Horario> horarios = new ArrayList<Horario>();

    public DiaDePractica() {
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dias")
    private List<Clase> clases = new ArrayList<Clase>();

    @Override
    public String toString() {
        return "DiaDePractica [diaDeLaSemana=" + diaDeLaSemana + ", idDia=" + idDia + "]";
    }

    public DiaDePractica(int idDia, String diaDeLaSemana, List<Clase> clases, List<Horario> horarios) {
        this.idDia = idDia;
        this.diaDeLaSemana = diaDeLaSemana;
        this.clases = clases;
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

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

}
