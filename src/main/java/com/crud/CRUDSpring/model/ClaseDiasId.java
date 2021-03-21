package com.crud.CRUDSpring.model;

import java.io.Serializable;

public class ClaseDiasId implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int dia;
    private int clase;

    @Override
    public String toString() {
        return "ClaseDiasId [dia=" + dia + ", clase=" + clase + "]";
    }

    public ClaseDiasId(int dia, int clase) {
        this.dia = dia;
        this.clase = clase;
    }

    public ClaseDiasId() {
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

}
