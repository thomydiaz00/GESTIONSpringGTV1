package com.crud.CRUDSpring.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.crud.CRUDSpring.interfaceService.IfServiceAsistencia;
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.Horario;

@Controller

public class ProfesorController {

	@Autowired
	interfaceProfesor interfaceProf;
	@Autowired
	IfServiceProfesor serviceProfesor;
	@Autowired
	IfServiceClase serviceClase;
	@Autowired
	interfaceAsistencia interfaceAsis;
	@Autowired
	IfServiceAsistencia serviceAsistencia;
	@Autowired
	IfServiceHorario serviceHorario;

	// Este metodo es el que usamos al enviar el form, evalua si el prof enviado
	// coincide con el dni
	@RequestMapping(value = "/profesor/redirect", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkSiExisteProfesor(@RequestParam("dniProfesor") int dni, RedirectAttributes ra) {
		try {
			Profesor profesor = interfaceProf.findByDniProf(dni).get();
			ra.addAttribute("profesor", profesor);
			return "redirect:/profesor/clases";

		} catch (Exception NoSuchElemenException) {
			ra.addAttribute("error", true);
			return "redirect:/login_profesor";

		}

	}

	@GetMapping("/profesor/clases")
	public String clasesProfesor(@ModelAttribute Profesor profesor, Model model,
			@RequestParam(value = "noHayHorarios", required = false) Optional<Boolean> noHayHorarios) {
		List<Clase> clases = new ArrayList<Clase>();
		clases = profesor.getClases(); // ver para hacer la relacion de
		model.addAttribute("profesor", profesor);
		model.addAttribute("clases", clases);
		return "vistas_profesor/logedin_profesor_clase";
	}

	@GetMapping("/profesor/redirect_to_horarios/{dniProf}/{idClase}")
	public String ConsultarHorariosProfesor(@PathVariable int dniProf, @PathVariable int idClase, RedirectAttributes ra,
			Model model) {
		Profesor profesor = interfaceProf.findByDniProf(dniProf).get();
		Clase clase = serviceClase.clasePorId(idClase).get();
		ra.addAttribute("profesor", profesor);
		ra.addAttribute("clase", clase);
		System.out.println("redirecting...");
		return "redirect:/profesor/consultar_horarios";
	}

	// @GetMapping("/profesor/consultar_horarios")
	// public String registrarAsistencia(Model model, @ModelAttribute Profesor
	// profesor, @ModelAttribute Clase clase) {
	// Date currentDate = new Date();
	// LocalDate tras =
	// currentDate.toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate();
	// List<Horario> horarios = clase.getHorarios();
	// List<Asistencia> asistencias = new ArrayList<Asistencia>();

	// model.addAttribute("indexHorarios", horarios.size());
	// model.addAttribute("idClase", clase.getIdClase());
	// model.addAttribute("idProfesor", profesor.getIdProf());
	// model.addAttribute("horarios", horarios);
	// for (Horario horario : horarios) {
	// try {
	// Asistencia asis =
	// interfaceAsis.findByHorarioInAndFechaAsistenciaInAndProfesor(horario, tras,
	// profesor)
	// .get();
	// asistencias.add(asis);
	// System.out.println(asis.isEstadoAsistencia());
	// } catch (NoSuchElementException e) {
	// Asistencia asis = new Asistencia();
	// asis.setEstadoAsistencia(false);
	// asistencias.add(asis);

	// }
	// }
	// System.out.println(asistencias.size());

	// model.addAttribute("asistencias", asistencias);
	// model.addAttribute("asistencia", new Asistencia());
	// return "vistas_profesor/consultar_horarios";
	// }

	@GetMapping("/profesor/save_asistencia")
	public String saveAsistencia(@RequestParam(value = "idhorario") int idHorario,
			@RequestParam(value = "idclase") int idClase, @RequestParam(value = "idprof") int idProf) {

		// get current time
		Date currentDate = new Date();
		LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		DayOfWeek currentDay = localCurrentDate.getDayOfWeek();
		DateTime currentDateTime = new DateTime();
		int currentHour = currentDateTime.getHourOfDay();
		// get the relationships
		Asistencia asistencia = new Asistencia();
		Clase clase = serviceClase.clasePorId(idClase).get();
		Profesor profesor = serviceProfesor.profesorPorId(idProf).get();
		Horario horario = serviceHorario.HorarioPorId(idHorario).get();
		// get horario elements
		// String fechaAsString = currentDateTime.toString().substring(0, 10);
		String horarioFinString = horario.getHora_fin();
		int horaFin = Integer.parseInt(horarioFinString.substring(0, 2));
		// Evalua, si ya hay una clase registrada en esta fecha para este horario,
		// informo
		String currentDayAsString = interfaceAsistencia.maskDay(currentDay);
		String diaAregistrar = horario.getDia().getDiaDeLaSemana();
		System.out.println("el dia que se quiere registrar es: " + diaAregistrar);
		boolean contieneDia = false;

		for (DiaDePractica diaDePractica : clase.getDias()) {
			if (diaDePractica.getDiaDeLaSemana().equalsIgnoreCase(currentDayAsString)) {
				contieneDia = true;
			}
		}

		// if (interfaceAsis.findByHorarioInAndFechaAsistenciaInAndProfesor(horario,
		// localCurrentDate, profesor)
		// .isPresent()) {
		// System.out.println("ya hay una clase registrada");
		// return "alertas/alerta_repetida.html";
		// }

		if (!contieneDia) {
			System.out.println("No puede fichar en este día");
			return "alertas/alerta_dia.html";

		} else if ((currentHour - horaFin) >= 3) {
			System.out.println("No puede cargar la asistencia pasadas 3 Horas de finalizada la clase");
			return "alertas/alerta_tarde.html";

		} else if ((currentHour - horaFin) < 0) {
			System.out.println("Solo puede cargas la asistencia una vez termine la clase");
			return "alertas/alerta_temprano.html";
		}
		if (contieneDia && diaAregistrar.equals(currentDayAsString)) {
			System.out.println("cargando la asistencia");
			// reemplazar por registroDeAsistencias
			// asistencia.setProfesor(profesor);
			// asistencia.setHorario(horario);
			asistencia.setFechaAsistencia(localCurrentDate);
			asistencia.setEstadoAsistencia(true);
			serviceAsistencia.guardarAsistencia(asistencia);
			return "alertas/alerta_success.html";

		} else {
			return "alertas/alerta_tarde.html";
		}

	}

}
