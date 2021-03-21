package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;

@Service
public class ClaseService implements IfServiceClase {

	@Autowired
	private interfaceClase data;

	@Override
	public List<Clase> listarClase() {
		return (List<Clase>) data.findAll();
	}

	@Override
	public int guardarClase(Clase c) {
		int res = 0;
		Clase clase = data.save(c);
		if (!clase.equals(null)) {
			res = 1;
		}
		// Horario horario = new Horario();
		// DiaDePractica dia = new DiaDePractica();
		// dia.setDiaDeLaSemana("MONDAY");
		// List<DiaDePractica> dias = new ArrayList<DiaDePractica>();
		// dias.add(dia);

		// crearRegistrosAsistencia(c, horario);
		return res;
	}

	// Ejemplo, se crea una lista con las fechas que correspondan a los dias que
	// tienen que marcarse
	public void crearRegistrosAsistencia(Clase c, Horario h) {
		// List<String> dias = new ArrayList<String>();
		// for (DiaDePractica dia : h.getDias()) {
		// dias.add(dia.getDiaDeLaSemana());
		// }
		// List<LocalDate> todasLasFechas =
		// c.getFechaInicio().datesUntil(c.getFechaFin()).collect(Collectors.toList());
		// List<LocalDate> filteredDates = new ArrayList<LocalDate>();
		// for (LocalDate date : todasLasFechas) {
		// System.out.println(date.getDayOfWeek().name());
		// if (dias.contains(date.getDayOfWeek().name())) {
		// System.out.println("dia lunes encontrado, -------------");
		// filteredDates.add(date);
		// }
		// }
		// System.out.println(filteredDates.toString());

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
