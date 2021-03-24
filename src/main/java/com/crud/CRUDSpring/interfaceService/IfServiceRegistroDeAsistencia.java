package com.crud.CRUDSpring.interfaceService;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.model.RegistroDeAsistencia;

public interface IfServiceRegistroDeAsistencia {
    public List<RegistroDeAsistencia> listarRegistroDeAsistencias();

    public Optional<RegistroDeAsistencia> RegistroDeAsistenciaPorId(int id);

    public int guardarRegistroDeAsistencia(RegistroDeAsistencia p);

    public void borrarRegistroDeAsistencia(int id);
}
