package com.crud.CRUDSpring.interfaceService;

import com.crud.CRUDSpring.model.DiaDePractica;
import java.util.List;
import java.util.Optional;

public interface IfServiceDia {
    public List<DiaDePractica> listarDiaDePractica();

    public Optional<DiaDePractica> DiaDePracticaPorId(int id);

    public int guardarDiaDePractica(DiaDePractica DiaDePractica);

    public void borrarDiaDePractica(int id);
}