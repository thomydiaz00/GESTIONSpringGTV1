package com.crud.CRUDSpring.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.model.Horario;
@Repository
public interface interfaceHorario extends CrudRepository<Horario, Integer> {

}
