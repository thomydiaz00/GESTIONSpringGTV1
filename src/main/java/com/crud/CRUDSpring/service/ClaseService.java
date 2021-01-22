package com.crud.CRUDSpring.service;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
@Service
public class ClaseService implements IfServiceClase {
	
	@Autowired
	private interfaceClase data;
	
	@Autowired
	private interfaceProfesor serviceProfesor;

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
		/*List<Profesor> profesores = (List<Profesor>) serviceProfesor.findAll();
		Optional<Clase> clase = data.findById(id);
		for (Iterator iterator = profesores.iterator(); iterator.hasNext();) {
			Profesor profesor = (Profesor) iterator.next();
			if(profesor.getClases().contains(clase.get())) {
				profesor.getClases().remove(clase.get());
				System.out.println("La clase esta asignada, seguro de eliminar? " + profesor.getNombreProf());
				serviceProfesor.save(profesor);
			}
		}*/
		data.deleteById(id);

	}



	@Override
	public Optional<Clase> clasePorId(int id) {
		return data.findById(id);
	}

	
}

	

	