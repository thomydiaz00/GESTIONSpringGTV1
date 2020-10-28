package com.crud.CRUDSpring.interfaces;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.CRUDSpring.entity.User;
import com.crud.CRUDSpring.model.Persona;

@Repository
public interface interfaceUser extends CrudRepository<User, String>{

	
}
