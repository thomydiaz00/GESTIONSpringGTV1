package com.crud.CRUDSpring.interfaces;

import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface interfaceRegistroDeAsistencia extends CrudRepository<RegistroDeAsistencia, Integer> {

    public Optional<RegistroDeAsistencia> findByHorarioInAndFechaDeCreacionInAndProfesor(Horario horario, LocalDate date, Profesor profesor);

}
