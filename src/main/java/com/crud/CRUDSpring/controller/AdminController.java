package com.crud.CRUDSpring.controller;

import java.time.DayOfWeek;
import java.util.Iterator;
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
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaceService.IfServiceUser;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
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
	private IfServiceUser servUser;
	@Autowired
	private interfaceAsistencia interfaceAsis;

	@GetMapping("/admin/lista_profesores")
	public String ListarProfesores(Model model) {
		List<Profesor> profesores = service.listarProfesores();
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

	private String maskDay(DayOfWeek currentDay) {
		switch (currentDay) {
		case MONDAY:
			return "Lunes";

		case TUESDAY:
			return "Martes";

		case WEDNESDAY:
			return "Miercoles";

		case THURSDAY:
			return "Jueves";

		case FRIDAY:
			return "Viernes";

		case SATURDAY:
			return "Sabado";

		case SUNDAY:
			return "Domingo";

		default:
			return null;
		}

	}

	// @GetMapping("admin/consultar_asistencia/{idProf}/{idClase}")
	// public String consultarAsistencia(Model model, @PathVariable int idProf,
	// @PathVariable int idClase,
	// RedirectAttributes ra) {

	// Clase clase = servClase.clasePorId(idClase).get();
	// Profesor profesor = service.profesorPorId(idProf).get();

	// Date currentDate = new Date();
	// LocalDate localCurrentDate =
	// currentDate.toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
	// .toLocalDate();

	// // for (Horario horario : clase.getHorarios()) {
	// // List<Asistencia> asis =
	// // interfaceAsis.findByHorarioInAndProfesor(horario,profesor);
	// // for (DiaDePractica dia : clase.getDias()) {
	// // String nomDia = dia.getDiaDeLaSemana();
	// // if (!nombresDias.contains(nomDia)) {
	// // nombresDias.add(nomDia);

	// // }
	// // }
	// // asistencias.addAll(asis);
	// // }

	// // System.out.println("xxxxxxxxxxxxxxxx Fin de carga xxxxxxxxxxxxxxxxxx");
	// // for (Asistencia asistencia : asistencias) {
	// // fechas.add(asistencia.getFechaAsistencia());
	// // }
	// // // Aca evaluamos. Si la cantidad de horarios corresponde con las
	// asistencias
	// // registradas para esa fecha, la asistencia está completa para ese dia
	// // Si es un solo horario, y tiene un registro de asistencia para la fecha, se
	// // agrega a la lista
	// // Si hay menos registros de los necesarios, sumamos a la lista de
	// incompletos
	// for (LocalDate fecha : fechas) {
	// String nombreDia = maskDay(fecha.getDayOfWeek());
	// int frecuencia =
	// interfaceAsis.contarNumeroDeAsistenciasPorFechaYNombreDia(fecha.toString(),
	// nombreDia);
	// int cantidadDeRegistrosNecesarios =
	// interfaceAsis.contarCantidadDeHorarios(nombreDia);
	// System.out.println("El horario de la fecha" + fecha.toString() + "debe tener
	// : "
	// + interfaceAsis.contarCantidadDeHorarios(nombreDia) + "registros para estar
	// completo");

	// if ((frecuencia % cantidadDeRegistrosNecesarios) == 0) {
	// if (!fechasFin.contains(fecha)) {
	// fechasFin.add(fecha);
	// }
	// } else {
	// if (!fechasIncompletas.contains(fecha)) {
	// fechasIncompletas.add(fecha);
	// }
	// }
	// }

	// System.out.println("Esta clase tiene esta cantidad de asistencias
	// registradas: " + asistencias.size());
	// System.out.println("----------------STATUSES------------------");
	// System.out.println("Las siguientes fechas estan completas");
	// for (LocalDate feFin : fechasFin) {
	// System.out.println(feFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	// }
	// System.out.println("----------------STATUSES------------------");
	// System.out.println("Las siguientes fechas estan incompletas");
	// for (LocalDate feIn : fechasIncompletas) {
	// System.out.println(feIn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	// }

	// model.addAttribute("nombresDias", nombresDias);
	// model.addAttribute("fechaCreacion", clase.getFechaInicio());
	// model.addAttribute("fechasIncompletas", fechasIncompletas);
	// model.addAttribute("fechasCompletas", fechasFin);
	// model.addAttribute("clase", idClase);
	// model.addAttribute("profesor", idProf);
	// return "asistencia_clase";

	// }

	// @GetMapping(value = "admin/request_asistencias")
	// public String getAsistenciasSheet(@RequestParam(value = "idprof") int idProf,
	// @RequestParam(value = "idclase") int idClase, @RequestParam(value = "month")
	// int month) {
	// System.out.println(idClase);
	// System.out.println(idProf);
	// System.out.println(month);
	// System.out.println(maskMonth(month));
	// return "index";
	// }

	// public String maskMonth(int month) {
	// return new DateFormatSymbols().getMonths()[month];
	// }
}
