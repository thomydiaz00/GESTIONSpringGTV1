package com.crud.CRUDSpring.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "registro_de_asistencia")
public class RegistroDeAsistencia {

    public RegistroDeAsistencia() {
    }

    @EmbeddedId
    private RegistroDiasId idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDia")
    @JoinColumn(name = "idDia")
    private DiaDePractica dia;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAsistencia")
    @JoinColumn(name = "idAsistencia")
    private Asistencia asistencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHorario")
    private Horario horario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProf")
    private Profesor profesor;

    @Column
    private LocalDate fechaDeCreacion = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
            .toLocalDate();
    @Column
    boolean estado;

    public RegistroDeAsistencia(RegistroDiasId idRegistro, DiaDePractica dia, Asistencia asistencia, Profesor profesor,
            Horario horario, LocalDate fechaDeCreacion, boolean estado) {
        this.idRegistro = idRegistro;
        this.dia = dia;
        this.asistencia = asistencia;
        this.fechaDeCreacion = fechaDeCreacion;
        this.estado = estado;
    }

    public RegistroDiasId getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(RegistroDiasId idRegistro) {
        this.idRegistro = idRegistro;
    }

    public DiaDePractica getDia() {
        return dia;
    }

    public void setDia(DiaDePractica dia) {
        this.dia = dia;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((asistencia == null) ? 0 : asistencia.hashCode());
        result = prime * result + ((dia == null) ? 0 : dia.hashCode());
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
        if (asistencia == null) {
            if (other.asistencia != null)
                return false;
        } else if (!asistencia.equals(other.asistencia))
            return false;
        if (dia == null) {
            if (other.dia != null)
                return false;
        } else if (!dia.equals(other.dia))
            return false;
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

}
