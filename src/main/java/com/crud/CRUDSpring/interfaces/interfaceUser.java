package com.crud.CRUDSpring.interfaces;


import org.springframework.data.repository.CrudRepository;

import com.crud.CRUDSpring.entity.User;
import com.crud.CRUDSpring.model.Persona;

public interface interfaceUser extends CrudRepository<User, String>{

	public static void main(String[] args) {
		
	}
	
}
