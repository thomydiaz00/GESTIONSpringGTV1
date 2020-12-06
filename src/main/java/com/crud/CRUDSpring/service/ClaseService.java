package com.crud.CRUDSpring.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
@Service
public class ClaseService implements IfServiceClase {
	
	@Autowired
	private interfaceClase data;

	@Override
	public List<Clase> listarClase() {
		return (List<Clase>)data.findAll();
	}

	

	@Override
	public int guardarClase(Clase c) {
		int res=0;
		Clase clase=data.save(c);
		if(!clase.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void borrarClase(int id) {
		data.deleteById(id);
		
	}



	@Override
	public Optional<Clase> clasePorId(int id) {
		return data.findById(id);
	}

	
}

	

	