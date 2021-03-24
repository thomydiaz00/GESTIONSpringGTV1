package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.CRUDSpring.entity.Authority;
import com.crud.CRUDSpring.interfaceService.IfServiceAuthority;
import com.crud.CRUDSpring.interfaces.interfaceAuthority;

@Service
public class AuthorityService implements IfServiceAuthority {
	@Autowired
	interfaceAuthority data;

	@Override
	public List<Authority> listarAuthorities() {
		// TODO Auto-generated method stub
		return (List<Authority>) data.findAll();
	}

	@Override
	public Optional<Authority> AuthorityPorId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardarAuthority(Authority authority) {
		int res = 0;
		Authority auto = data.save(authority);
		if (!auto.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public void borrarAuthority(int id) {
		data.deleteById(id);
	}

}
