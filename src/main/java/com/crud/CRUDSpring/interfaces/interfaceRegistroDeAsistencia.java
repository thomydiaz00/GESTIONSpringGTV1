package com.crud.CRUDSpring.interfaces;

import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.crud.CRUDSpring.model.RegistroDiasId;
import com.crud.CRUDSpring.model.Asistencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface interfaceRegistroDeAsistencia extends CrudRepository<RegistroDeAsistencia, Integer> {

    public Optional<RegistroDeAsistencia> findByIdRegistro(RegistroDiasId id);
    public Optional<RegistroDeAsistencia> findByIdRegistroAsistencia(Asistencia asistencia);

    public int countByFechaDeFichadoAndIdRegistro(LocalDate date, RegistroDiasId id);

}
