package com.crud.CRUDSpring.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.interfaceService.IfServiceRegistroDeAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceHorario;
import com.crud.CRUDSpring.interfaces.interfaceRegistroDeAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.crud.CRUDSpring.model.RegistroDiasId;

@Service
public class HorarioService implements IfServiceHorario {
	@Autowired
	interfaceHorario data;
	@Autowired
	interfaceClase clases;
	@Autowired
	interfaceAsistencia interfaceAsis;
	@Autowired
	interfaceRegistroDeAsistencia interfaceRegistroAsis;
	@Autowired
	IfServiceRegistroDeAsistencia serviceRegistroAsistencias;

	@Override
	public List<Horario> listarHorarios() {

		return (List<Horario>) data.findAll();
	}

	@Override
	public Optional<Horario> HorarioPorId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardarHorario(Horario horario) {
		int estado = 0;
		Horario h;
		boolean esNuevoHorario = data.findById(horario.getIdHorario()).isPresent();
		if (!esNuevoHorario) {
			h = crearRegistrosDeAsistencia(horario.getClase(), horario);
		} else {
			h = data.save(horario);
		}
		if (!h.equals(null)) {
			estado = 1;
		}

		return estado;
	}

	@Override
	public void borrarHorario(int id) {
		data.deleteById(id);

	}

	public Horario crearRegistrosDeAsistencia(Clase clase, Horario horario) {
		List<Asistencia> asistencias = interfaceAsis.findByClase(clase);
		Horario h = data.save(horario);
		
		for (Profesor profesor : clase.getProfesores()) {
			for (Asistencia asistencia : asistencias) {
				DayOfWeek dayName = asistencia.getFechaAsistencia().getDayOfWeek();
				String diaHorario = horario.getDia().getDiaDeLaSemana();
				RegistroDiasId id = new RegistroDiasId(horario, asistencia, profesor);

				if ((interfaceAsistencia.maskDay(dayName).equals(diaHorario)
						&& !interfaceRegistroAsis.findByIdRegistro(id).isPresent())) {
					RegistroDeAsistencia registroDeAsistencia = new RegistroDeAsistencia();
					registroDeAsistencia.setIdRegistro(id);
					registroDeAsistencia.setFechaDeFichado(asistencia.getFechaAsistencia());
					registroDeAsistencia.setDias(clase.getDias());
					registroDeAsistencia.setEstado(false);
					serviceRegistroAsistencias.guardarRegistroDeAsistencia(registroDeAsistencia);
				}
			}
		}
		if (clase.getProfesores().isEmpty())
			System.out.println("La lista de profs esta vacia");
		return h;

	}
}
