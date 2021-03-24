package com.crud.CRUDSpring.interfaceService;

import com.crud.CRUDSpring.model.Asistencia;
import java.util.List;
import java.util.Optional;

public interface IfServiceAsistencia {
    public List<Asistencia> listarAsistencia();

    public Optional<Asistencia> AsistenciaPorId(int id);

    public int guardarAsistencia(Asistencia Asistencia);

    public void borrarAsistencia(int id);
}
