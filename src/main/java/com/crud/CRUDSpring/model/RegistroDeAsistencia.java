package com.crud.CRUDSpring.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "registro_de_asistencia")
public class RegistroDeAsistencia {

    public RegistroDeAsistencia() {
    }

    @EmbeddedId
    private RegistroDiasId idRegistro;

    @Column
    LocalDate fechaDeFichado;
    @Column
    boolean estado;

    public RegistroDeAsistencia(RegistroDiasId idRegistro, Asistencia asistencia, Profesor profesor,
            LocalDate fechaDeCreacion, LocalDate fechaDeFichado, boolean estado) {
        this.idRegistro = idRegistro;
        this.fechaDeFichado = fechaDeFichado;
        this.estado = estado;
    }

    public RegistroDiasId getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(RegistroDiasId idRegistro) {
        this.idRegistro = idRegistro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDeFichado() {
        return fechaDeFichado;
    }

    public void setFechaDeFichado(LocalDate fechaDeFichado) {
        this.fechaDeFichado = fechaDeFichado;
    }

    @Override
    public String toString() {
        return "estado=" + estado + ", idRegistro=" + idRegistro + ",fechaDeFichado: " + fechaDeFichado + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (estado ? 1231 : 1237);
        result = prime * result + ((fechaDeFichado == null) ? 0 : fechaDeFichado.hashCode());
        result = prime * result + ((idRegistro == null) ? 0 : idRegistro.hashCode());
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
        return true;
    }

}
