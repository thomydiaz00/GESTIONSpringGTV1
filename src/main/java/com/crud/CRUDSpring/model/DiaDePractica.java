package com.crud.CRUDSpring.model;

import javax.persistence.Table;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Table(name = "DiaDePractica")
public class DiaDePractica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idDia;
    @Column
    private String diaDeLaSemana;

    // @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<RegistroDeAsistencia> registroDeAsistencias = new
    // ArrayList<RegistroDeAsistencia>();

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

    public DiaDePractica(int idDia, String diaDeLaSemana, List<Clase> clases, List<Horario> horarios,
            List<RegistroDeAsistencia> registroDeAsistencias) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clases == null) ? 0 : clases.hashCode());
        result = prime * result + ((diaDeLaSemana == null) ? 0 : diaDeLaSemana.hashCode());
        result = prime * result + ((horarios == null) ? 0 : horarios.hashCode());
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
        if (clases == null) {
            if (other.clases != null)
                return false;
        } else if (!clases.equals(other.clases))
            return false;
        if (diaDeLaSemana == null) {
            if (other.diaDeLaSemana != null)
                return false;
        } else if (!diaDeLaSemana.equals(other.diaDeLaSemana))
            return false;
        if (horarios == null) {
            if (other.horarios != null)
                return false;
        } else if (!horarios.equals(other.horarios))
            return false;
        if (idDia != other.idDia)
            return false;
        return true;
    }

}
