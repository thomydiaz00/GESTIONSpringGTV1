package com.crud.CRUDSpring.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "registro_de_asistencia")
public class RegistroDeAsistencia {

    public RegistroDeAsistencia() {
    }

    @EmbeddedId
    private RegistroDiasId idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHorario")
    private Horario horario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf")
    private Profesor profesor;

    @Column
    private LocalDate fechaDeCreacion;
    // = new
    // Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate();

    @Column
    private LocalDate fechaDeFichado;
    @Column
    boolean estado;

    public RegistroDeAsistencia(RegistroDiasId idRegistro, DiaDePractica dia, Asistencia asistencia, Horario horario,
            Profesor profesor, LocalDate fechaDeCreacion, LocalDate fechaDeFichado, boolean estado) {
        this.idRegistro = idRegistro;
        this.horario = horario;
        this.profesor = profesor;
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (estado ? 1231 : 1237);
        result = prime * result + ((fechaDeCreacion == null) ? 0 : fechaDeCreacion.hashCode());
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
        if (estado != other.estado)
            return false;
        if (fechaDeCreacion == null) {
            if (other.fechaDeCreacion != null)
                return false;
        } else if (!fechaDeCreacion.equals(other.fechaDeCreacion))
            return false;
        if (idRegistro == null) {
            if (other.idRegistro != null)
                return false;
        } else if (!idRegistro.equals(other.idRegistro))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "estado=" + estado + ", fechaDeCreacion=" + fechaDeCreacion + ", fechaDeFichado=" + fechaDeFichado
                + ", horario=" + horario + ", idRegistro=" + idRegistro + ", profesor=" + profesor + "]";
    }

}
