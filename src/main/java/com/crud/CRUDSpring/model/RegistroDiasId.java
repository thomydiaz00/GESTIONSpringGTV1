package com.crud.CRUDSpring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegistroDiasId implements Serializable {
 
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "idAsistencia")
    private int asistencia;
    @Column(name = "idDia")
    private int dia;
    
    public int getAsistencia() {
        return asistencia;
    }
    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public RegistroDiasId(int asistencia, int dia) {
        this.asistencia = asistencia;
        this.dia = dia;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + asistencia;
        result = prime * result + dia;
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
        if (asistencia != other.asistencia)
            return false;
        if (dia != other.dia)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "RegistroDiasId [asistencia=" + asistencia + ", dia=" + dia + "]";
    }
    

}
