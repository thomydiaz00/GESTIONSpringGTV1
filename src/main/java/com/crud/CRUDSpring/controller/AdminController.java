package com.crud.CRUDSpring.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.TomcatDataSourcePoolMetadata;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.CRUDSpring.interfaceService.IfServiceAsistencia;
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaceService.IfServiceRegistroDeAsistencia;
import com.crud.CRUDSpring.interfaceService.IfServiceUser;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceRegistroDeAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.RegistroDeAsistencia;

@Controller
public class AdminController {

	@Autowired
	private interfaceProfesor interfaceProf;
	@Autowired
	private IfServiceProfesor serviceProfesor;
	@Autowired
	private IfServiceClase servClase;
	@Autowired
	private IfServiceUser servUser;
	@Autowired
	private IfServiceRegistroDeAsistencia servRegistro;
	@Autowired
	interfaceRegistroDeAsistencia registroDeAsistencia;
	@Autowired
	IfServiceAsistencia serviceAsistencia;
	@Autowired
	interfaceAsistencia interfaceAsis;

	@GetMapping("/admin/lista_profesores")
	public String ListarProfesores(Model model) {
		List<Profesor> profesores = serviceProfesor.listarProfesores();
		List<Clase> clases = servClase.listarClase();
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("clases", clases);
		model.addAttribute("profesores", profesores);
		// System.out.println(interfaceAsis.contarNumeroDeAsistenciasPorFechaYNombreDia("2021-02-21",
		// "PEPE"));
		return "listas/lista_profesores";
	}

	@GetMapping("/admin/lista_profesores/nuevo")
	public String agregar(Model model) {
		/*
		 * Se crea una nueva instancia de profesor a rellenar con los datos que se envie
		 * en el form, se carga una lista con todas las clases en el model para despues
		 * poder seleccionar las clases por prof
		 */

		List<Clase> clases = servClase.listarClase();
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("clases", clases);
		return "forms/form_profesor";

	}

	/*
	 * Muestra las clases asignadas al profesor profClases es el atributo con las
	 * clases asignadas, todasLasClases son las clases que se pueden asignar.
	 * ClasesParaAsignar es el profesor con las nuevas clases, si se crea llama va
	 * al controlador actualizarClases()
	 */
	@GetMapping("/admin/lista_profesores_clases/{idProf}")
	public String lista_profesores_completa(@PathVariable int idProf, Model model) {
		// Muestra la lista con clases disponibles para agregar
		Optional<Profesor> profesor = serviceProfesor.profesorPorId(idProf);
		List<Clase> profClases = profesor.get().getClases();
		List<Clase> todasLasClases = servClase.listarClase();
		for (Clase clase : profClases) {
			todasLasClases.remove(clase);
		}
		Profesor clasesParaAsignar = new Profesor();

		clasesParaAsignar.setIdProf(profesor.get().getIdProf());
		model.addAttribute("todasLasClases", todasLasClases);
		model.addAttribute("clases", profClases);
		model.addAttribute("profesor", profesor.get());
		model.addAttribute("clasesParaAsignar", clasesParaAsignar);
		return "listas/lista_profesores_clases";

	}

	/*
	 * Obtiene un profesor con una lista de nuevas clases, se trae al profesor
	 * guardado en la bd, se le agregan las clases y se guarda nuevamente en la bd
	 */
	@PostMapping("/admin/actualizar_clases")
	public String actualizarClases(HttpServletRequest request, Profesor clasesParaAsignar, Model model) {
		Profesor p = serviceProfesor.profesorPorId(clasesParaAsignar.getIdProf()).get();
		for (Clase clase : clasesParaAsignar.getClases()) {
			p.getClases().add(clase);
		}
		serviceProfesor.guardarProfesor(p);
		// Se redirige a la pagina anterior
		return "redirect:" + request.getHeader("Referer");

	}

	@PostMapping("/admin/save")
	public String save(@Valid Profesor p, Model model, RedirectAttributes ra) {
		Optional<Profesor> profesor = interfaceProf.findByDniProf(p.getDniProf());
		if (profesor.isPresent()) {
			if (p.getIdProf() != profesor.get().getIdProf() && p.getDniProf() == profesor.get().getDniProf()) {
				ra.addFlashAttribute("mensaje",
						"No pudo registrarse el profesor, el DNI asignado ya está registrado en el sistema");
			}
		} else {
			serviceProfesor.guardarProfesor(p);
		}
		return "redirect:/admin/lista_profesores";
	}

	@GetMapping("/admin/editar_profesor/{id}")
	public String editar(@PathVariable int id, Model model) { // Uso PathVariable para establecer id como parametro
		Optional<Profesor> profesor = serviceProfesor.profesorPorId(id);
		List<Clase> clases = servClase.listarClase();
		model.addAttribute("profesor", profesor);
		model.addAttribute("clases", clases);
		return "forms/form_profesor";

	}

	@GetMapping("admin/delete_profesor/{idProf}")
	public String eliminar(@PathVariable int idProf, Model model) {

		serviceProfesor.borrarProfesor(idProf);
		return "redirect:/admin/lista_profesores";
	}

	@GetMapping("admin/delete_usuario/{id}")
	public String eliminarUsuario(@PathVariable int id, Model model) {
		servUser.borrarUser(id);
		return "redirect:/admin/lista_profesores";
	}

	/*
	 * Se pasan el id del profesor y la clase como parametros, se obtiene el
	 * profesor y la clase que correspondan, luego se elimina la clase que coincida
	 * con la asignada
	 */
	@GetMapping("admin/borrar_clase_profesor/{idProf}/{idClase}")
	public String eliminarClase(@PathVariable int idProf, @PathVariable int idClase, Model model) {
		Profesor profesor = serviceProfesor.profesorPorId(idProf).get();
		Clase clase = servClase.clasePorId(idClase).get();
		profesor.getClases().remove(clase);
		serviceProfesor.guardarProfesor(profesor);
		return "redirect:/admin/lista_profesores_clases/{idProf}";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout"; // You can redirect wherever you want, but generally it's a good practice to
											// show login screen again.
	}

	@GetMapping("admin/consultar_asistencia/{idProf}/{idClase}")
	public String consultarAsistencia(Model model, @PathVariable int idProf, @PathVariable int idClase,
			RedirectAttributes ra) {
		java.time.LocalDate currentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		Clase clase = servClase.clasePorId(idClase).get();
		Profesor profesor = serviceProfesor.profesorPorId(idProf).get();
		List<LocalDate> fechasCompletas = new ArrayList<LocalDate>();
		List<LocalDate> fechasIncompletas = new ArrayList<LocalDate>();
		List<String> dias = new ArrayList<String>();
		for (DiaDePractica dia : clase.getDias()) {
			dias.add(dia.getDiaDeLaSemana());
		}

		List<LocalDate> faltas = new ArrayList<LocalDate>();

		for (Asistencia asistencia : interfaceAsis.findByClaseAndProfesorAndEstadoAsistencia(clase, profesor, true)) {
			String nombreDia = interfaceAsistencia.maskDay(asistencia.getFechaAsistencia().getDayOfWeek());
			if (dias.contains(nombreDia)) {
				fechasCompletas.add(asistencia.getFechaAsistencia());
			}
		}
		// ver que el dia de la fecha pertenece a los dias antes de agregar como falta
		for (Asistencia falta : interfaceAsis.findByClaseAndProfesorAndEstadoAsistencia(clase, profesor, false)) {
			int res = falta.getFechaAsistencia().compareTo(currentDate);
			String nombreDia = interfaceAsistencia.maskDay(falta.getFechaAsistencia().getDayOfWeek());

			if (res <= 0 && !falta.getRegistrosDeAsistencia().isEmpty() && dias.contains(nombreDia)) {
				if (dias.contains(nombreDia)) {
					faltas.add(falta.getFechaAsistencia());
				}
			}

		}
		System.out.println("fechas completas: " + fechasCompletas.toString());
		System.out.println("faltas hasta el dia " + currentDate + ": " + faltas.toString());

		model.addAttribute("fechasCompletas", fechasCompletas);
		model.addAttribute("faltas", faltas);
		model.addAttribute("clase", clase);
		model.addAttribute("profesor", idProf);
		return "asistencia_clase";

	}

	@GetMapping("admin/consulta_horarios")
	public String consultaHorarios(@RequestParam(value = "date") String date,
			@RequestParam(value = "clase") int idClase, @RequestParam(value = "profesor") int idProfesor, Model model) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		

		Optional<Profesor> profesor = serviceProfesor.profesorPorId(idProfesor);
		Optional<Clase> clase = servClase.clasePorId(idClase);
		Optional<Asistencia> asistencia = Optional.empty();
		List<RegistroDeAsistencia> registros = new ArrayList<RegistroDeAsistencia>();

		if (clase.isPresent() && profesor.isPresent()) {
			asistencia = interfaceAsis.findByFechaAsistenciaInAndProfesorInAndClase(localDate, profesor.get(),
					clase.get());
		}
		if (asistencia.isPresent()) {
			for (RegistroDeAsistencia registro : asistencia.get().getRegistrosDeAsistencia()) {
				if (registro.getIdRegistro().getHorario().getDia().getDiaDeLaSemana().equals(interfaceAsistencia.maskDay(localDate.getDayOfWeek()))) {
					registros.add(registro);
				}
			}
		}
		model.addAttribute("horarios", registros);
		model.addAttribute("selectedFecha", date );
		return "asistencia_clase :: horariosList";

	}
	@GetMapping(value="admin/asistencia_manual/{idProf}/{idClase}")
	public String asistenciaManual(@PathVariable(value="idProf") int idProf, @PathVariable(value="idClase") int idClase, Model model){
		java.time.LocalDate currentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		Optional<Profesor> profesor = serviceProfesor.profesorPorId(idProf); 
		Optional<Clase> clase = servClase.clasePorId(idClase);
		List<Asistencia> todasLasAsistencias = new ArrayList<Asistencia>();
		List<Asistencia> asistencias = new ArrayList<Asistencia>();

		if(profesor.isPresent() && clase.isPresent()){
			todasLasAsistencias = interfaceAsis.findByClaseAndProfesorAndEstadoAsistencia(clase.get(), profesor.get(), false);
		}
		for(Asistencia asistencia : todasLasAsistencias){
			if(currentDate.compareTo(asistencia.getFechaAsistencia()) >= 0){
				asistencias.add(asistencia);
			}
		}
		model.addAttribute("asistencias", asistencias);
		model.addAttribute("idProf", idProf);
		model.addAttribute("idClase", idClase);

		return "forms/form_asistencia";
	}
	@PostMapping(value="admin/guardar_asistencia_manual/{idProf}/{idClase}")
	public String guardarAsistenciaManual(Model model, Asistencia asistencia, @PathVariable(value="idProf") int idProf, @PathVariable(value="idClase") int idClase){
		Optional<Asistencia> a = serviceAsistencia.AsistenciaPorId(asistencia.getIdAsistencia());
		if(a.isPresent()){
			Asistencia asis= a.get();
			for(RegistroDeAsistencia registro : asis.getRegistrosDeAsistencia()){
				registro.setEstado(true);
				servRegistro.guardarRegistroDeAsistencia(registro);
			}
			asis.setEstadoAsistencia(true);
			serviceAsistencia.guardarAsistencia(asis);
		}
		return "redirect:/admin/consultar_asistencia/" + idProf + "/" + idClase ;
	}

}
