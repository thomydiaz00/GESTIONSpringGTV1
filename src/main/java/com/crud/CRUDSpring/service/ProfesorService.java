package com.crud.CRUDSpring.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
@Service
public class ProfesorService implements IfServiceProfesor {
	
	@Autowired
	private interfaceProfesor data;
	@Autowired
	private interfaceClase serviceClase;

	@Override
	public List<Profesor> listarProfesores() {
		return (List<Profesor>)data.findAll();
	}

	@Override
	public Optional<Profesor> profesorPorId(int id) {
		return data.findById(id);
		
	}

	@Override
	public int guardarProfesor(Profesor p) {
		int res=0;
		Profesor profesor=data.save(p);
		if(!p.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void borrarProfesor(int id) {
		
		data.deleteById(id);
		//check si tiene clases asignadas antes de eliminar
		
	}
}
