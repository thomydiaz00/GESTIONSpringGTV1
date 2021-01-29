package com.crud.CRUDSpring.interfaceService;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.entity.User;
import com.crud.CRUDSpring.model.Clase;

public interface IfServiceUser {
	public List<User> listarUser();
	public Optional<User> UserPorId(int id);
	public int guardarUser( User user);
	public void borrarUser(int id);
	
}
