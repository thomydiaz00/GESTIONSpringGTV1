package com.crud.CRUDSpring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clase_tiene_dias")
@IdClass(ClaseDiasId.class)
public class ClaseTieneDias {

    @Id
    @ManyToOne
    @JoinColumn(name = "idClase")
    private Clase clase;

    @Id
    @ManyToOne
    @JoinColumn(name = "idDia")
    private DiaDePractica dia;

    public ClaseTieneDias() {
    }

    public ClaseTieneDias(int idClaseDias, Clase clase, DiaDePractica dia) {
        this.clase = clase;
        this.dia = dia;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public DiaDePractica getDia() {
        return dia;
    }

    public void setDia(DiaDePractica dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "ClaseTieneDias [clase=" + clase + ", dia=" + dia + "]";
    }

}
