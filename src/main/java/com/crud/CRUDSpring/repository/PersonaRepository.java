package com.crud.CRUDSpring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.CRUDSpring.model.Persona;

/*
 * JpaRepository extends PagingAndSorting, que extends CrudRepository
 * Entonces JpaRepository provee los metodos relacionados con la persistencia
 * 	de datos (metodos que permiten manipular los datos de la b.d). Al extender
 *  CrudRepository tambien proporciona los metodos de CRUD
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	   
	 @Query("SELECT p FROM Persona p WHERE p.name LIKE %?1%"
	            + " OR p.telefono LIKE %?1%"
	            + " OR p.id LIKE %?1%")
	 
    public List<Persona> search(String keyword);
}