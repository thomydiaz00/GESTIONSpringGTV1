package com.crud.CRUDSpring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.crud.CRUDSpring.interfaceService.IfServiceAsistencia;
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;

import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Horario;

@Service
public class ClaseService implements IfServiceClase {

	@Autowired
	private interfaceClase data;

	@Autowired
	interfaceAsistencia interfaceAsis;
	@Autowired
	AsistenciaService serviceAsistencia;

	@Override
	public List<Clase> listarClase() {
		return (List<Clase>) data.findAll();
	}

	@Override
	public int guardarClase(Clase c) {
		int res = 0;
		boolean esNuevaClase = false;
		if (!data.existsById(c.getIdClase())) {
			esNuevaClase = true;
			Clase clase = data.save(c);
			if (!clase.equals(null)) {
				res = 1;
			}
		}
		crearRegistrosAsistencia(c, esNuevaClase);

		return res;
	}

	private void crearRegistrosAsistencia(Clase clase, boolean esNuevaClase) {
		System.out.println("creando los reg");
		List<String> dias = new ArrayList<String>();
		List<LocalDate> filteredDates = new ArrayList<LocalDate>();
		List<LocalDate> todasLasFechas = new ArrayList<LocalDate>();
		Clase claseSinActualizar = clasePorId(clase.getIdClase()).get();
		boolean diasActualizados = false;
		boolean fechaFinActualizada = false;

		int sizeOfActualDays = clase.getDias().size();
		int sizeOfOldDays = claseSinActualizar.getDias().size();

		if (sizeOfActualDays != sizeOfOldDays) {
			diasActualizados = true;
		}
		if (!claseSinActualizar.getFechaFin().equals(clase.getFechaFin())) {
			fechaFinActualizada = true;
		}
		for (DiaDePractica diaDePractica : clase.getDias()) {
			dias.add(diaDePractica.getDiaDeLaSemana());
		}
		// Si es una nueva clase creo todos los registros, si es una clase que existía
		// creo registros
		// A partir de la fecha fin de la clase sin actualizarla todavía

		filteredDates = definirTodasLasFechas(clase, claseSinActualizar, dias, esNuevaClase, fechaFinActualizada);

		data.save(clase);
		System.out.println("------------------------------");
		System.out.println(todasLasFechas.toString());
		System.out.println("------------------------------");
		System.out.println(dias.toString());

		// Si la fecha está dentro del rango y contiene alguno de los dias, la agrego a
		// una lista
		for (LocalDate fecha : filteredDates) {
			Asistencia asistencia = new Asistencia();
			asistencia.setFechaAsistencia(fecha);
			asistencia.setClase(clase);
			serviceAsistencia.guardarAsistencia(asistencia);
			System.out.println(fecha + " -" + fecha.getDayOfWeek().toString());
		}
	}

	private List<LocalDate> definirTodasLasFechas(Clase clase, Clase claseSinActualizar, List<String> dias,
			boolean esNuevaClase, boolean fechaFinActualizada) {
		List<LocalDate> todasLasFechas = new ArrayList<LocalDate>();
		List<LocalDate> filteredDates = new ArrayList<LocalDate>();

		if (esNuevaClase || !fechaFinActualizada) {
			todasLasFechas = clase.getFechaInicio().datesUntil(clase.getFechaFin()).collect(Collectors.toList());
		} else {
			todasLasFechas = claseSinActualizar.getFechaFin().datesUntil(clase.getFechaFin())
					.collect(Collectors.toList());
		}
		for (LocalDate date : todasLasFechas) {
			System.out.println("esta clase no es nueva");
			if (!interfaceAsis.findByFechaAsistenciaInAndClase(date, clase).isPresent()) {
				String dayName = interfaceAsistencia.maskDay(date.getDayOfWeek());
				if (dias.contains(dayName)) {
					filteredDates.add(date);
				}
			}

		}
		return filteredDates;
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
