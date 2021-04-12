package com.crud.CRUDSpring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;

@Service
public class ClaseService implements IfServiceClase {

	@Autowired
	private interfaceClase data;

	@Autowired
	interfaceAsistencia interfaceAsis;
	@Autowired
	AsistenciaService serviceAsistencia;
	@Autowired
	HorarioService serviceHorario;

	@Override
	public List<Clase> listarClase() {
		return (List<Clase>) data.findAll();
	}

	@Override
	public int guardarClase(Clase c) {
		int res = 0;
		boolean esNuevaClase = false;
		boolean esNuevoProfesor = false;
		Optional<Profesor> profesor = Optional.empty();
		if (!data.existsById(c.getIdClase())) {
			esNuevaClase = true;
			Clase clase = data.save(c);
			if (!clase.equals(null)) {
				res = 1;
			}
		}
		System.out.println("asistencias actuales: " + c.getAsistencias().size());
		crearFechasAsistencia(c, esNuevaClase, esNuevoProfesor, profesor);
		// Si se actualiza la

		return res;
	}

	public void crearFechasAsistencia(Clase clase, boolean esNuevaClase, boolean esNuevoProfesor,
			Optional<Profesor> nuevoProfesor) {
		List<String> dias = new ArrayList<String>();
		List<Asistencia> asistencias = clase.getAsistencias();
		List<LocalDate> filteredDates = new ArrayList<LocalDate>();
		Clase claseSinActualizar = clasePorId(clase.getIdClase()).get();
		boolean fechaFinActualizada = false;
		boolean profesorActualizado = false;
		if (!claseSinActualizar.getProfesores().equals(clase.getProfesores())) {
			profesorActualizado = true;
		}
		if (!claseSinActualizar.getFechaFin().equals(clase.getFechaFin())) {
			fechaFinActualizada = true;
		}
		for (DiaDePractica diaDePractica : clase.getDias()) {
			dias.add(diaDePractica.getDiaDeLaSemana());
		}

		filteredDates = definirTodasLasFechas(clase, claseSinActualizar, dias, esNuevaClase, fechaFinActualizada,
				profesorActualizado);

		// Si la fecha coincide con los dias de practica asosciados a la clase los
		// agrego a la base de datos
		if (!esNuevoProfesor) {
			for (Profesor profesor : clase.getProfesores()) {
				for (LocalDate fecha : filteredDates) {
					System.out.println("-------------------------------");
					Asistencia asistencia = new Asistencia();
					asistencia.setClase(clase);
					asistencia.setProfesor(profesor);
					asistencia.setFechaAsistencia(fecha);
					asistencias.add(asistencia);
					System.out.println(fecha + " -" + fecha.getDayOfWeek().toString());

				}
			}
		} else {
			for (LocalDate fecha : filteredDates) {
				Asistencia asistencia = new Asistencia();
				asistencia.setClase(clase);
				asistencia.setProfesor(nuevoProfesor.get());
				asistencia.setFechaAsistencia(fecha);
				asistencias.add(asistencia);
			}

		}
		for (Asistencia asistencia : asistencias) {
			if (!interfaceAsis.findByFechaAsistenciaInAndProfesorInAndClase(asistencia.getFechaAsistencia(),
					asistencia.getProfesor(), asistencia.getClase()).isPresent()) {
				serviceAsistencia.guardarAsistencia(asistencia);
			}

		}
		clase.setAsistencias(asistencias);
		data.save(clase);

		for (Horario horario : clase.getHorarios()) {
			serviceHorario.crearRegistrosDeAsistencia(clase, horario);
		}
	}

	/*
	 * Esta funcion trae una lista con los registros a agregar Si es una clase nueva
	 * o no se actualizó la fecha de finalizacion de clases devuelvo todas las
	 * fechas de inicio-fin. Si la clase ya existe devuelvo las fechas a incorporar
	 * que no existan desde la antigua fecha de fin hasta la nueva fecha fin (esto
	 * evita que haya registros con la misma fecha para la misma clase). Si se
	 * actualiza el día de la clase también creo registros para ese dia
	 */
	private List<LocalDate> definirTodasLasFechas(Clase clase, Clase claseSinActualizar, List<String> dias,
			boolean esNuevaClase, boolean fechaFinActualizada, boolean profesorActualizado) {
		List<LocalDate> todasLasFechas = new ArrayList<LocalDate>();
		List<LocalDate> filteredDates = new ArrayList<LocalDate>();

		if (esNuevaClase || !fechaFinActualizada || (clase.getDias() != claseSinActualizar.getDias())) {
			System.out.println("actualizando desde el principio");
			todasLasFechas = clase.getFechaInicio().datesUntil(clase.getFechaFin()).collect(Collectors.toList());
		} else {
			todasLasFechas = claseSinActualizar.getFechaFin().datesUntil(clase.getFechaFin())
					.collect(Collectors.toList());

		}
		for (LocalDate date : todasLasFechas) {
			for (Profesor profesor : clase.getProfesores()) {
				System.out.println("esta clase no es nueva");
				if (!interfaceAsis.findByFechaAsistenciaInAndProfesorInAndClase(date, profesor, clase).isPresent()) {
					String dayName = interfaceAsistencia.maskDay(date.getDayOfWeek());
					if (dias.contains(dayName) && !filteredDates.contains(date)) {
						filteredDates.add(date);
					}
				}
			}
		}
		System.out.println("------------Fechas-a-agregar----------------");
		System.out.println(filteredDates.toString());

		return filteredDates;
	}

	@Override
	public int borrarClase(int id) {
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

	public void crearFechasAsistenciaProfesor(Clase clase) {
	}

	public void crearRegistrosProfesor(Profesor profesor, Clase clase) {
	}

}
