package com.crud.CRUDSpring.model;
import java.io.Serializable;
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
    @JoinColumn(name = "idHorario")
    private Horario horario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAsistencia")
    private Asistencia asistencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProf")
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((asistencia == null) ? 0 : asistencia.hashCode());
        result = prime * result + ((horario == null) ? 0 : horario.hashCode());
        result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
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
        RegistroDiasId other = (RegistroDiasId) obj;
        if (asistencia == null) {
            if (other.asistencia != null)
                return false;
        } else if (!asistencia.equals(other.asistencia))
            return false;
        if (horario == null) {
            if (other.horario != null)
                return false;
        } else if (!horario.equals(other.horario))
            return false;
        if (profesor == null) {
            if (other.profesor != null)
                return false;
        } else if (!profesor.equals(other.profesor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "RegistroDiasId [asistencia=" + asistencia + ", horario=" + horario + ", profesor=" + profesor + "]";
    }

}
