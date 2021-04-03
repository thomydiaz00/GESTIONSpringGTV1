package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;

@Service
public class ProfesorService implements IfServiceProfesor {

	@Autowired
	private interfaceProfesor data;
	@Autowired
	private ClaseService serviceClase;
	@Autowired
	private HorarioService serviceHorario;
	@Autowired
	private interfaceAsistencia interfaceAsis;

	@Override
	public List<Profesor> listarProfesores() {
		return (List<Profesor>) data.findAll();
	}

	@Override
	public Optional<Profesor> profesorPorId(int id) {
		return data.findById(id);

	}

	@Override
	public int guardarProfesor(Profesor p) {
		int res = 0;

		Profesor profesor = data.save(p);
		Optional<Profesor> nuevoProfesor = Optional.of(profesor);
		for (Clase clase : p.getClases()) {
			serviceClase.crearFechasAsistencia(clase, true, true, nuevoProfesor);
		}
		if (!profesor.equals(null)) {
			res = 1;
		}
		return res;
	}

	@Override
	public void borrarProfesor(int id) {

		data.deleteById(id);
		// check si tiene clases asignadas antes de eliminar

	}
}
