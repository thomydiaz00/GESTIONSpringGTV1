package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.entity.User;
import com.crud.CRUDSpring.interfaceService.IfServiceUser;
import com.crud.CRUDSpring.interfaces.interfaceUser;

@Service
public class UserService implements IfServiceUser {

	@Autowired 
	interfaceUser data;
	@Override
	public List<User> listarUser() {
		return (List<User>)data.findAll();
	}
	
	@Override
	public Optional<User> UserPorId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardarUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		user.setPassword(encoder.encode(user.getPassword()));
		int res = 0;
		User usuario = data.save(user);
		
		if(!usuario.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public void borrarUser(int id) {
		data.deleteById(id);
		
	}
	


	


}
