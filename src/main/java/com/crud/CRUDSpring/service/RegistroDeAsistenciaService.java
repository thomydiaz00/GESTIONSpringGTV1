package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.interfaceService.IfServiceRegistroDeAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceRegistroDeAsistencia;
import com.crud.CRUDSpring.model.RegistroDeAsistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeAsistenciaService implements IfServiceRegistroDeAsistencia {
    @Autowired
    interfaceRegistroDeAsistencia data;

    @Override
    public List<RegistroDeAsistencia> listarRegistroDeAsistencias() {
        // TODO Auto-generated method stub
        return (List<RegistroDeAsistencia>) data.findAll();
    }

    @Override
    public Optional<RegistroDeAsistencia> RegistroDeAsistenciaPorId(int id) {
        // TODO Auto-generated method stub
        return data.findById(id);
    }

    @Override
    public int guardarRegistroDeAsistencia(RegistroDeAsistencia p) {
        // TODO Auto-generated method stub
        int estado = 0;
        RegistroDeAsistencia h = data.save(p);
        if (!h.equals(null)) {
            estado = 1;
        }
        return estado;
    }

    @Override
    public void borrarRegistroDeAsistencia(int id) {
        data.deleteById(id);
    }

}
