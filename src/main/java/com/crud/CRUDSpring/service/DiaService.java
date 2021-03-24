package com.crud.CRUDSpring.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.CRUDSpring.interfaceService.IfServiceDia;
import com.crud.CRUDSpring.interfaces.interfaceDia;
import com.crud.CRUDSpring.model.DiaDePractica;

@Service
public class DiaService implements IfServiceDia {

    @Autowired
    private interfaceDia data;

    @Override
    public List<DiaDePractica> listarDiaDePractica() {
        return (List<DiaDePractica>) data.findAll();
    }

    @Override
    public int guardarDiaDePractica(DiaDePractica c) {
        int res = 0;
        DiaDePractica DiaDePractica = data.save(c);
        if (!DiaDePractica.equals(null)) {
            res = 1;
        }
        return res;
    }

    @Override
    public void borrarDiaDePractica(int id) {
        data.deleteById(id);

    }

    @Override
    public Optional<DiaDePractica> DiaDePracticaPorId(int id) {
        return data.findById(id);
    }
}
