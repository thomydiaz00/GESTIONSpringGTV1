package com.crud.CRUDSpring.interfaces;

import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.crud.CRUDSpring.model.RegistroDiasId;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface interfaceRegistroDeAsistencia extends CrudRepository<RegistroDeAsistencia, Integer> {

    public Optional<RegistroDeAsistencia> findByHorarioInAndFechaDeCreacionInAndProfesor(Horario horario,
            LocalDate date, Profesor profesor);

    public Optional<RegistroDeAsistencia> findByIdRegistro(RegistroDiasId id);

}
