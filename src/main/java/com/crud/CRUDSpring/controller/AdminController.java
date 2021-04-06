package com.crud.CRUDSpring.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.crud.CRUDSpring.interfaceService.IfServiceUser;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.interfaces.interfaceRegistroDeAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.crud.CRUDSpring.model.RegistroDiasId;
import com.crud.CRUDSpring.repository.ProfesorRepository;

@Controller
public class AdminController {

	@Autowired
	private ProfesorRepository query;
	@Autowired
	private IfServiceProfesor serviceProfesor;
	@Autowired
	private IfServiceClase servClase;
	@Autowired
	private IfServiceUser servUser;
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
		return "lista_profesores";
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
		return "form_profesor";

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
		return "lista_profesores_clases";

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
	public String save(@Valid Profesor p, Model model) {
		serviceProfesor.guardarProfesor(p);
		return "redirect:/admin/lista_profesores";
	}

	@GetMapping("/admin/prueba")
	public String prueba(Model model) {
		model.addAttribute("cantidad", query.count());
		return "user";
	}

	@GetMapping("/admin/editar_profesor/{id}")
	public String editar(@PathVariable int id, Model model) { // Uso PathVariable para establecer id como parametro
		Optional<Profesor> profesor = serviceProfesor.profesorPorId(id);
		List<Clase> clases = servClase.listarClase();
		model.addAttribute("profesor", profesor);
		model.addAttribute("clases", clases);
		return "form_profesor";

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
			System.out.println("agregndo fecha completa para el dia: " + nombreDia);
			if (dias.contains(nombreDia)) {
				fechasCompletas.add(asistencia.getFechaAsistencia());
			}
		}
		// ver que el dia de la fecha pertenece a los dias antes de agregar como falta
		for (Asistencia falta : interfaceAsis.findByClaseAndProfesorAndEstadoAsistencia(clase, profesor, false)) {
			int res = falta.getFechaAsistencia().compareTo(currentDate);
			String nombreDia = interfaceAsistencia.maskDay(falta.getFechaAsistencia().getDayOfWeek());

			if (res <= 0 && !falta.getRegistrosDeAsistencia().equals(null)) {
				if (dias.contains(nombreDia)) {
					faltas.add(falta.getFechaAsistencia());
				}
			}

		}
		System.out.println("fechas completas: " + fechasCompletas.toString());
		System.out.println("faltas hasta el dia " + currentDate + ": " + faltas.toString());

		model.addAttribute("fechasCompletas", fechasCompletas);
		model.addAttribute("faltas", faltas);
		model.addAttribute("clase", idClase);
		return "asistencia_clase";

	}

	@GetMapping("admin/consulta_horarios")
	public String consultaHorarios(@RequestParam(value = "date") String date,
			@RequestParam(value = "clase") String clase) {
		System.out.println("recieved: " + date);
		System.out.println("recieved: " + clase);

		return "index";

	}

}
