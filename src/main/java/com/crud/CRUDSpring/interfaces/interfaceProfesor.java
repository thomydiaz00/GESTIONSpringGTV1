package com.crud.CRUDSpring.interfaces;
import com.crud.CRUDSpring.model.Profesor;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface interfaceProfesor extends CrudRepository<Profesor, Integer>{
	public Optional<Profesor> findByDniProf(Long dniProf);

}
