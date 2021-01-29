package com.crud.CRUDSpring.interfaces;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.entity.User;
/*
 * JPA implementa los metodos, hay que aclarar los atributos a buscar
 * como parametros y pasarlos como están expresados en el modelo
 * de la entidad
 */
@Repository
public interface interfaceUser extends CrudRepository<User, Integer>{
	public User findByUsername(String username);
	public User findByIdAndPassword( int id,String password);
	
	
}
