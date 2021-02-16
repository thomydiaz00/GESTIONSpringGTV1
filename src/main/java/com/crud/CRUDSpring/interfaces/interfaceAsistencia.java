package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Horario;

@Repository
public interface interfaceAsistencia extends CrudRepository<Asistencia, Integer> {
    public Optional<Asistencia> findByHorarioInAndFechaAsistencia(Horario horario, LocalDate tras);
}
