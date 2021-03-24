package com.crud.CRUDSpring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		if (!data.existsById(c.getIdClase())) {
			esNuevaClase = true;
			Clase clase = data.save(c);
			if (!clase.equals(null)) {
				res = 1;
			}
		}
		crearFechasAsistencia(c, esNuevaClase);
		//Si se actualiza la 
		
		return res;
	}

	private void crearFechasAsistencia(Clase clase, boolean esNuevaClase) {
		System.out.println("creando los reg");
		List<String> dias = new ArrayList<String>();
		List<LocalDate> filteredDates = new ArrayList<LocalDate>();
		Clase claseSinActualizar = clasePorId(clase.getIdClase()).get();
		boolean fechaFinActualizada = false;

		if (!claseSinActualizar.getFechaFin().equals(clase.getFechaFin())) {
			fechaFinActualizada = true;
		}
		for (DiaDePractica diaDePractica : clase.getDias()) {
			dias.add(diaDePractica.getDiaDeLaSemana());
		}

		filteredDates = definirTodasLasFechas(clase, claseSinActualizar, dias, esNuevaClase, fechaFinActualizada);
		data.save(clase);

		// Si la fecha coincide con los dias de practica asosciados a la clase los
		// agrego a la base de datos
		for (LocalDate fecha : filteredDates) {
			Asistencia asistencia = new Asistencia();
			asistencia.setFechaAsistencia(fecha);
			asistencia.setClase(clase);
			serviceAsistencia.guardarAsistencia(asistencia);
			System.out.println(fecha + " -" + fecha.getDayOfWeek().toString());
		}
	}

	/*
	 * Esta funcion trae una lista con los registros a agregar en la b.d: Si es una
	 * clase nueva o no se actualizó la fecha de finalizacion de clases devuelvo
	 * todas las fechas de inicio-fin. Si la clase ya existe devuelvo las fechas a
	 * incorporar que no existan en la b.d desde la antigua fecha de fin hasta la
	 * nueva fecha fin (esto evita que haya registros con la misma fecha para la
	 * misma clase)
	 */
	private List<LocalDate> definirTodasLasFechas(Clase clase, Clase claseSinActualizar, List<String> dias,
			boolean esNuevaClase, boolean fechaFinActualizada) {
		List<LocalDate> todasLasFechas = new ArrayList<LocalDate>();
		List<LocalDate> filteredDates = new ArrayList<LocalDate>();

		if (esNuevaClase || !fechaFinActualizada) {
			todasLasFechas = clase.getFechaInicio().datesUntil(clase.getFechaFin()).collect(Collectors.toList());
		} else {
			todasLasFechas = claseSinActualizar.getFechaFin().datesUntil(clase.getFechaFin())
					.collect(Collectors.toList());
			//Si la fecha de fin se actualiza tambien deben actualizarse los registros de asistencias
			if ( !clase.getHorarios().isEmpty()) {
				for(Horario horario : clase.getHorarios()){
					serviceHorario.crearRegistrosDeAsistencia(clase, horario);
				}
			}
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
		System.out.println("------------Todas-las-fechas----------------");
		System.out.println(dias.toString());
		System.out.println(todasLasFechas.toString());
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

}
