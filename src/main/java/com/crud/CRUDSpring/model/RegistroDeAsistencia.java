package com.crud.CRUDSpring.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "registro_de_asistencia")
public class RegistroDeAsistencia {

    public RegistroDeAsistencia() {
    }

    @EmbeddedId
    private RegistroDiasId idRegistro;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "idProf")
    // private Profesor profesor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "registro_tiene_dias", joinColumns = { @JoinColumn(name = "id_asistencia"),
            @JoinColumn(name = "id_horario"), @JoinColumn(name = "id_prof") }, inverseJoinColumns = { @JoinColumn(name = "id_dia") })
    private List<DiaDePractica> dias = new ArrayList<DiaDePractica>();

    @Column
    private LocalDate fechaDeCreacion;
    // = new
    // Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate();

    @Column
    private LocalDate fechaDeFichado;
    @Column
    boolean estado;

    public RegistroDeAsistencia(RegistroDiasId idRegistro, Asistencia asistencia, List<DiaDePractica> dias,
            Profesor profesor, LocalDate fechaDeCreacion, LocalDate fechaDeFichado, boolean estado) {
        this.idRegistro = idRegistro;
        this.dias = dias;
        // this.profesor = profesor;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeFichado = fechaDeFichado;
        this.estado = estado;
    }

    public RegistroDiasId getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(RegistroDiasId idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDate getfechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setfechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // public Profesor getProfesor() {
    // return profesor;
    // }

    // public void setProfesor(Profesor profesor) {
    // this.profesor = profesor;
    // }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public LocalDate getFechaDeFichado() {
        return fechaDeFichado;
    }

    public void setFechaDeFichado(LocalDate fechaDeFichado) {
        this.fechaDeFichado = fechaDeFichado;
    }

    public List<DiaDePractica> getDias() {
        return dias;
    }

    public void setDias(List<DiaDePractica> dias) {
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "estado=" + estado + ", fechaDeCreacion=" + fechaDeCreacion + ", fechaDeFichado=" + fechaDeFichado
                + ", dias=" + dias.size() + ", idRegistro=" + idRegistro + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dias == null) ? 0 : dias.hashCode());
        result = prime * result + (estado ? 1231 : 1237);
        result = prime * result + ((fechaDeCreacion == null) ? 0 : fechaDeCreacion.hashCode());
        result = prime * result + ((fechaDeFichado == null) ? 0 : fechaDeFichado.hashCode());
        result = prime * result + ((idRegistro == null) ? 0 : idRegistro.hashCode());
        // result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
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
        RegistroDeAsistencia other = (RegistroDeAsistencia) obj;
        if (dias == null) {
            if (other.dias != null)
                return false;
        } else if (!dias.equals(other.dias))
            return false;
        if (estado != other.estado)
            return false;
        if (fechaDeCreacion == null) {
            if (other.fechaDeCreacion != null)
                return false;
        } else if (!fechaDeCreacion.equals(other.fechaDeCreacion))
            return false;
        if (fechaDeFichado == null) {
            if (other.fechaDeFichado != null)
                return false;
        } else if (!fechaDeFichado.equals(other.fechaDeFichado))
            return false;
        if (idRegistro == null) {
            if (other.idRegistro != null)
                return false;
        } else if (!idRegistro.equals(other.idRegistro))
            return false;
        // if (profesor == null) {
        // if (other.profesor != null)
        // return false;
        // } else if (!profesor.equals(other.profesor))
        // return false;
        return true;
    }

}
