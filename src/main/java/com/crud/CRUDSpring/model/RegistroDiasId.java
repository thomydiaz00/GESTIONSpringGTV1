package com.crud.CRUDSpring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RegistroDiasId implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHorario", nullable = false, insertable = false, updatable = false)
    private Horario horario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAsistencia", nullable = false, insertable = false, updatable = false)
    private Asistencia asistencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProfesor", nullable = false, insertable = false, updatable = false)
    private Profesor profesor;

    public RegistroDiasId(Horario horario, Asistencia asistencia, Profesor profesor) {
        this.horario = horario;
        this.asistencia = asistencia;
        this.profesor = profesor;
    }

    public RegistroDiasId() {
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "RegistroDiasId [asistencia=" + asistencia + ", horario=" + horario + ", profesor=" + profesor + "]";
    }

}
