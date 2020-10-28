package com.crud.CRUDSpring.interfaceService;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.entity.User;

public interface IfServiceUser {
	
	public List<User> listarUser(String busquedaUser);
	public Optional <User> UserlistarId(int id);
	public int saveUser(User u);
	public void deleteUser(int id);

	
}
