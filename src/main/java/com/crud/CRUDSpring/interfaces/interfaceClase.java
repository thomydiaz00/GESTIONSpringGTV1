package com.crud.CRUDSpring.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;

@Repository
public interface interfaceClase extends CrudRepository<Clase, Integer> {

}
