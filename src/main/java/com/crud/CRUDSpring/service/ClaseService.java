package com.crud.CRUDSpring.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
		return (List<Clase>) data.findAll();
	}

	@Override
	public int guardarClase(Clase c) {
		int res = 0;
		Date currentDate = new Date();
		LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		c.setFechaCreacion(localCurrentDate);
		Clase clase = data.save(c);
		if (!clase.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public int borrarClase(int id) {
		/*
		 * List<Profesor> profesores = (List<Profesor>) serviceProfesor.findAll();
		 * Optional<Clase> clase = data.findById(id); for (Iterator iterator =
		 * profesores.iterator(); iterator.hasNext();) { Profesor profesor = (Profesor)
		 * iterator.next(); if(profesor.getClases().contains(clase.get())) {
		 * profesor.getClases().remove(clase.get());
		 * System.out.println("La clase esta asignada, seguro de eliminar? " +
		 * profesor.getNombreProf()); serviceProfesor.save(profesor); } }
		 */
		data.deleteById(id);
		boolean isPresent = data.existsById(id);
		if (isPresent) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Optional<Clase> clasePorId(int id) {
		return data.findById(id);
	}

}
