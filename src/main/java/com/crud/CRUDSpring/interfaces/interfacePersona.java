package com.crud.CRUDSpring.interfaces;
import com.crud.CRUDSpring.model.Persona;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;





/*
 * Crud repository provee las funciones CRUD Create, read, update, delete
 * las cuales van a ser implementadas en
 * */
@Repository
public interface interfacePersona extends CrudRepository<Persona, Integer> {

	

}
