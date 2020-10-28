package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.entity.User;
import com.crud.CRUDSpring.interfaceService.IfServiceUser;
import com.crud.CRUDSpring.interfaces.interfacePersona;
import com.crud.CRUDSpring.interfaces.interfaceUser;
import com.crud.CRUDSpring.model.Persona;
import com.crud.CRUDSpring.repository.PersonaRepository;
import com.crud.CRUDSpring.repository.UserRepository;

@Service
public class UserService implements IfServiceUser{
@Autowired
	
	private interfaceUser data; 
	
	@Autowired
	private UserRepository search;

	/*@Override
	public List<User> listarUser(String busquedaUser) {
		data.findby
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> UserlistarId(int id) {
		// TODO Auto-generated method stub
		return null;
		data.find
	}

	@Override
	public int saveUser(User u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	 * Implementacion metodo de busqueda. Si se pasa alguna keyword (parametro para busqueda)
	 * va a ejecutar el query, del cual los resultados que coincidan se van a guardar en una lista
	 * de personas. En caso de que no se pase ninguna keyword, simplemente va a listar a todos los clientes
	 */
	
	
	
	

	
}
