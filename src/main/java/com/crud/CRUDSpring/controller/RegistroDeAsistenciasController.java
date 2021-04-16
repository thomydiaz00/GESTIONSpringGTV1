package com.crud.CRUDSpring.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
import com.crud.CRUDSpring.interfaceService.IfServiceRegistroDeAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceRegistroDeAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.crud.CRUDSpring.model.RegistroDiasId;
import com.crud.CRUDSpring.model.Horario;

@Controller

public class RegistroDeAsistenciasController {

	@Autowired
	interfaceProfesor interfaceProf;
	@Autowired
	IfServiceProfesor serviceProfesor;
	@Autowired
	IfServiceClase serviceClase;
	@Autowired
	interfaceAsistencia interfaceAsis;
	@Autowired
	interfaceRegistroDeAsistencia interfaceRegistroDeAsistencia;
	@Autowired
	IfServiceRegistroDeAsistencia serviceRegistroDeAsistencia;
	@Autowired
	IfServiceAsistencia serviceAsistencia;
	@Autowired
	IfServiceHorario serviceHorario;

	// Este metodo evalua si el prof enviado en la pestaña login coincide con el dni
	// login_profesor está en AppController
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
		List<Clase> clases = profesor.getClases();
		List<Clase> clasesDeHoy =  new ArrayList<Clase>();
		String currentDay = getCurrentDay();
		System.out.println("hoy es:" + currentDay);
		for(Clase cla : clases){
			for(DiaDePractica dia :cla.getDias()){
				if(dia.getDiaDeLaSemana().equals(currentDay)){
					clasesDeHoy.add(cla);
				}
			}
		}
		System.out.println(clasesDeHoy.toString());
		model.addAttribute("profesor", profesor);
		model.addAttribute("clases", clasesDeHoy);
		return "vistas_profesor/profesor_clases";
	}

	private String getCurrentDay() {
		LocalDate localCurrentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		String currentDay = interfaceAsistencia.maskDay(localCurrentDate.getDayOfWeek());
		return currentDay;
	}

	@GetMapping("/profesor/redirect_to_horarios/{dniProf}/{idClase}")
	public String RedirectToConsultarHorariosProfesor(@PathVariable int dniProf, @PathVariable int idClase,
			RedirectAttributes ra, Model model) {
		Profesor profesor = interfaceProf.findByDniProf(dniProf).get();
		Clase clase = serviceClase.clasePorId(idClase).get();
		ra.addAttribute("profesor", profesor);
		ra.addAttribute("clase", clase);
		System.out.println("redirecting...");
		return "redirect:/profesor/consultar_horarios";
	}

	@GetMapping("/profesor/consultar_horarios")
	public String ConsultarHorariosProfesor(Model model, @ModelAttribute Profesor profesor,
			@ModelAttribute Clase clase) {
		List<RegistroDeAsistencia> asistencias = new ArrayList<RegistroDeAsistencia>();
		LocalDate localCurrentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		for(Asistencia asistencia : clase.getAsistencias()){
			if(asistencia.getFechaAsistencia().toString().equals(localCurrentDate.toString()) && asistencia.getProfesor() == profesor && asistencia.getClase() == clase){
				asistencias.addAll(asistencia.getRegistrosDeAsistencia());
			}
		}
		System.out.println(asistencias.size());
		model.addAttribute("profesor", profesor);
		model.addAttribute("clase", clase);
		model.addAttribute("asistencias", asistencias);
		model.addAttribute("currentDay", getCurrentDay());
		return "vistas_profesor/consultar_horarios";
		// Date date = new Date();
		// LocalDate currentDate =
		// date.toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate();
		// List<Horario> horarios = clase.getHorarios();
		// List<Asistencia> asistencias = new ArrayList<Asistencia>();
	}

	@GetMapping("/profesor/save_asistencia")
	public String saveAsistencia(@RequestParam(value = "idhorario") int idHorario,
			@RequestParam(value = "idclase") int idClase, @RequestParam(value = "idprof") int idProf,
			RedirectAttributes ra) {
		// get current time
		LocalDate localCurrentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		DayOfWeek currentDay = localCurrentDate.getDayOfWeek();
		int currentHour = new DateTime().getHourOfDay();
		// get the relationships
		Clase clase = serviceClase.clasePorId(idClase).get();
		Profesor profesor = serviceProfesor.profesorPorId(idProf).get();
		Horario horario = serviceHorario.HorarioPorId(idHorario).get();
		// get Hora Fin
		int horaFin = Integer.parseInt(horario.getHora_fin().substring(0, 2));
		boolean asistenciaCargada = false;

		try {
			Asistencia asistencia = interfaceAsis
					.findByFechaAsistenciaInAndProfesorInAndClase(localCurrentDate, profesor, clase).get();
		} catch (NoSuchElementException e) {
			System.out.println("No se encontró la fecha de asistencia para la clase, " + e.getMessage());
			return "vistas_profesor/login_profesor";
		}

		String currentDayAsString = interfaceAsistencia.maskDay(currentDay);
		String diaAregistrar = horario.getDia().getDiaDeLaSemana();

		boolean contieneDia = false;

		// El dia actual está entre los elementos de la lista
		for (DiaDePractica diaDePractica : clase.getDias()) {
			if (diaDePractica.getDiaDeLaSemana().equalsIgnoreCase(currentDayAsString)) {
				contieneDia = true;
			}
		}

		if (!contieneDia) {
			System.out.println("No puede fichar en este día");
			return "alertas/alerta_dia.html";

		} else if ((currentHour - horaFin) >= 3) {
			System.out.println("No puede cargar la asistencia pasadas 3 Horas de finalizada la clase");
			return "alertas/alerta_tarde.html";

		} else if ((currentHour - horaFin < 0)) {
			System.out.println("Solo puede cargas la asistencia una vez termine la clase");
			return "alertas/alerta_temprano.html";
		}

		if (diaAregistrar.equals(currentDayAsString)) {
			Asistencia asistencia = interfaceAsis
					.findByFechaAsistenciaInAndProfesorInAndClase(localCurrentDate, profesor, clase).get();
			RegistroDiasId id = new RegistroDiasId(horario, asistencia, profesor);
			Optional<RegistroDeAsistencia> registro = interfaceRegistroDeAsistencia.findByIdRegistro(id);
			int cantidadDeRegistros = 0;

			if (registro.isPresent()) {
				RegistroDeAsistencia reg = registro.get();
				if (reg.isEstado()) {
					System.out.println("asistencia ya fue cargada anteriormente");
					return "alertas/alerta_repetida.html";
				}
			}
			for (RegistroDeAsistencia reg : asistencia.getRegistrosDeAsistencia()) {
				if (reg.getIdRegistro().equals(id)) {
					System.out.println("--------------");
					reg.setEstado(true);
					asistenciaCargada = true;
					serviceRegistroDeAsistencia.guardarRegistroDeAsistencia(reg);
					System.out.println(reg.getIdRegistro().getAsistencia().getFechaAsistencia() + ":  cargada");
					// Registro guardado
				}
				if (reg.isEstado()) {
					cantidadDeRegistros++;
				}
			}
			if (cantidadDeRegistros == asistencia.getRegistrosDeAsistencia().size()) {
				System.out.println("--------------");
				System.out.println("todos los horarios cargados para este dia");
				asistencia.setEstadoAsistencia(true);
				serviceAsistencia.guardarAsistencia(asistencia);
			}

			return "alertas/alerta_success.html";
		} else {

			return "alertas/alerta_tarde.html";
		}
	}

}
