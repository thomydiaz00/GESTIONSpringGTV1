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
    @JoinColumn(name = "idDia", nullable = false, insertable = false, updatable = false)
    private DiaDePractica dia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAsistencia", nullable = false, insertable = false, updatable = false)
    private Asistencia asistencia;

    public RegistroDiasId(DiaDePractica dia, Asistencia asistencia) {
        this.dia = dia;
        this.asistencia = asistencia;
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

    @Override
    public String toString() {
        return "RegistroDiasId [asistencia=" + asistencia + ", dia=" + dia + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((asistencia == null) ? 0 : asistencia.hashCode());
        result = prime * result + ((dia == null) ? 0 : dia.hashCode());
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
        if (dia == null) {
            if (other.dia != null)
                return false;
        } else if (!dia.equals(other.dia))
            return false;
        return true;
    }

    public RegistroDiasId() {
    }

    


}
