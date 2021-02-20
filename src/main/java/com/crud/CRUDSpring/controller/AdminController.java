package com.crud.CRUDSpring.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.text.DateFormatSymbols;
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

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Persona;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.service.ClaseService;
import com.crud.CRUDSpring.service.ProfesorService;
import com.crud.CRUDSpring.repository.ProfesorRepository;

@Controller
public class AdminController {

	@Autowired
	private ProfesorRepository query;
	@Autowired
	private IfServiceProfesor service;
	@Autowired
	private IfServiceClase servClase;
	@Autowired
	private interfaceAsistencia interfaceAsis;

	@GetMapping("/admin/lista_profesores")
	public String ListarProfesores(Model model) {
		List<Profesor> profesores = service.listarProfesores();
		List<Clase> clases = servClase.listarClase();
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("clases", clases);
		model.addAttribute("profesores", profesores);
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
		Optional<Profesor> profesor = service.profesorPorId(idProf);
		List<Clase> profClases = profesor.get().getClases();
		List<Clase> todasLasClases = servClase.listarClase();
		for (Iterator iterator = profClases.iterator(); iterator.hasNext();) {
			Clase clase = (Clase) iterator.next();
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
		Profesor p = service.profesorPorId(clasesParaAsignar.getIdProf()).get();
		for (Iterator iterator = clasesParaAsignar.getClases().iterator(); iterator.hasNext();) {
			Clase clase = (Clase) iterator.next();
			p.getClases().add(clase);
		}
		service.guardarProfesor(p);
		// Se redirige a la pagina anterior
		return "redirect:" + request.getHeader("Referer");

	}

	@PostMapping("/admin/save")
	public String save(@Valid Profesor p, Model model) {
		service.guardarProfesor(p);
		return "redirect:/admin/lista_profesores";
	}

	@GetMapping("/admin/prueba")
	public String prueba(Model model) {
		model.addAttribute("cantidad", query.count());
		return "user";
	}

	@GetMapping("/admin/editar_profesor/{id}")
	public String editar(@PathVariable int id, Model model) { // Uso PathVariable para establecer id como parametro
		Optional<Profesor> profesor = service.profesorPorId(id);
		List<Clase> clases = servClase.listarClase();
		model.addAttribute("profesor", profesor);
		model.addAttribute("clases", clases);
		return "form_profesor";

	}

	@GetMapping("admin/delete_profesor/{idProf}")
	public String eliminar(@PathVariable int idProf, Model model) {

		service.borrarProfesor(idProf);
		return "redirect:/admin/lista_profesores";
	}

	/*
	 * Se pasan el id del profesor y la clase como parametros, se obtiene el
	 * profesor y la clase que correspondan, luego se elimina la clase que coincida
	 * con la asignada
	 */
	@GetMapping("admin/borrar_clase_profesor/{idProf}/{idClase}")
	public String eliminarClase(@PathVariable int idProf, @PathVariable int idClase, Model model) {
		Profesor profesor = service.profesorPorId(idProf).get();
		Clase clase = servClase.clasePorId(idClase).get();
		profesor.getClases().remove(clase);
		service.guardarProfesor(profesor);
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
		Clase clase = servClase.clasePorId(idClase).get();
		Profesor profesor = service.profesorPorId(idProf).get();

		String fecha = null;
		List<String> fechas = new ArrayList<String>();
		List<String> fechasFin = new ArrayList<String>();
		List<Asistencia> asistencias = new ArrayList<Asistencia>();
		List<Horario> horarios = clase.getHorarios();
		for (Horario horario : clase.getHorarios()) {
			asistencias.addAll(interfaceAsis.findByHorarioInAndProfesor(horario, profesor));
		}
		System.out.println("Esta clase tiene esta cantidad de asistencias registradas: " + asistencias.size());
		for (Asistencia asistencia : asistencias) {
			fecha = asistencia.getFechaAsistencia().now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			fechas.add(fecha);
			System.out.println("Asistencia registrada el día: " + fecha);
		}
		// aca, si todos los horarios están agregamos a la lista. Corregir que se agrega
		// la fecha mas de una vez
		if (horarios.size() > 1) {
			System.out.println("esta clase tiene tantos horarios: " + horarios.size());
			for (String fecha2 : fechas) {
				if (Collections.frequency(fechas, fecha2) == horarios.size()) {
					fechasFin.add(fecha2);
				}
			}
		}
		model.addAttribute("indexFechas", fechasFin.size());
		model.addAttribute("fechas", fechasFin);
		model.addAttribute("clase", idClase);
		model.addAttribute("profesor", idProf);
		return "asistencia_clase";

	}

	@GetMapping(value = "admin/request_asistencias")
	public String getAsistenciasSheet(@RequestParam(value = "idprof") int idProf,
			@RequestParam(value = "idclase") int idClase, @RequestParam(value = "month") int month) {
		System.out.println(idClase);
		System.out.println(idProf);
		System.out.println(month);
		System.out.println(maskMonth(month));
		return "index";
	}

	public String maskMonth(int month) {
		return new DateFormatSymbols().getMonths()[month];
	}

}
