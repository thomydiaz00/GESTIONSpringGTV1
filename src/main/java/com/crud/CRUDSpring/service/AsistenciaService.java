package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.interfaceService.IfServiceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.model.Asistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.IfServiceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.model.Asistencia;

@Service
public class AsistenciaService implements IfServiceAsistencia {
    @Autowired
    interfaceAsistencia data;
    @Autowired
    interfaceAsistencia Asistencia;

    @Override
    public List<Asistencia> listarAsistencia() {

        return (List<Asistencia>) data.findAll();
    }

    @Override
    public Optional<Asistencia> AsistenciaPorId(int id) {

        return data.findById(id);
    }

    @Override
    public int guardarAsistencia(Asistencia Asistencia) {
        int estado = 0;
        Asistencia h = data.save(Asistencia);
        if (!h.equals(null)) {
            estado = 1;
        }
        return estado;
    }

    @Override
    public void borrarAsistencia(int id) {
        data.deleteById(id);

    }
}
