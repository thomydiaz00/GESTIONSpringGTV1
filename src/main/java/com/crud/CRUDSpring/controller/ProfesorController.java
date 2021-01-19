package com.crud.CRUDSpring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Persona;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.service.ProfesorService;
import com.crud.CRUDSpring.repository.ProfesorRepository;

@Controller
public class ProfesorController {
		
		@Autowired
		private ProfesorRepository query;
		@Autowired
		private IfServiceProfesor service;
		@Autowired
		private IfServiceClase servClase;
		private Optional<Clase> clase;
		
		@GetMapping("/admin/lista_profesores")
		public String ListarProfesores(Model model) {
	        List<Profesor> profesores= service.listarProfesores();
	        List<Clase> cla = servClase.listarClase();
	        
			model.addAttribute("clases", cla);
			model.addAttribute("profesores", profesores);
	        return "lista_profesores";
	    }
		
		@GetMapping("/admin/lista_profesores/nuevo")
		public String agregar(Model model) {
			/* Se crea una nueva instancia de profesor a rellenar con los datos
			 * que se envie en el form, se carga una lista con todas las 
			 * clases en el model para despues poder seleccionar las clases por prof
			 * */
			
			List<Clase> clases = servClase.listarClase();
			model.addAttribute("profesor", new Profesor());
			model.addAttribute("clases", clases);
			return "form_profesor";
			
		}
		@GetMapping("/admin/lista_profesores_clases/{id}")
		public String lista_profesores_completa(@PathVariable int id, Model model) {
			Optional<Profesor> profesor = service.profesorPorId(id);
			List<Clase> profClases = profesor.get().getClases();
			List<Clase> todasLasClases = servClase.listarClase();
			for (Iterator iterator = profClases.iterator(); iterator.hasNext();) {
				Clase clase = (Clase) iterator.next();
				todasLasClases.remove(clase);
				
			}
			model.addAttribute("todasLasClases", todasLasClases);
			model.addAttribute("clases",profClases);
			model.addAttribute("profesor", profesor.get());
			return "lista_profesores_clases";
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
		@GetMapping("/admin/asignar_clase/{idProf}")
		public String asignarClase(@PathVariable int idProf,@PathVariable int idClase, Model model) {
			Profesor profesor = service.profesorPorId(idProf).get();
			Clase clase = servClase.clasePorId(idClase).get();
			profesor.getClases().add(clase);
			service.guardarProfesor(profesor);
			return "redirect:/admin/lista_profesores_clases/{idProf}";
		}
		@GetMapping("/admin/editar_profesor/{id}")
		public String editar(@PathVariable int id, Model model) {		//Uso PathVariable para establecer id como parametro
			Optional<Profesor> profesor= service.profesorPorId(id);
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
		 * Se pasan el id del profesor y la clase como parametros,
		 * se obtiene el profesor y la clase que correspondan,
		 * luego se elimina la clase que coincida con la asignada
		 * */
		@GetMapping("admin/borrar_clase_profesor/{idProf}/{idClase}")
		public String eliminarClase(@PathVariable int idProf,@PathVariable int idClase, Model model) {
			Profesor profesor= service.profesorPorId(idProf).get();
			Clase clase = servClase.clasePorId(idClase).get();
			System.out.println(clase.getNombreDep());
			System.out.println("eliminando la clase" + idClase);
			profesor.getClases().remove(clase);
			System.out.println("Clase elimminada");
			service.guardarProfesor(profesor);
			return "redirect:/admin/lista_profesores_clases/{idProf}";
			

		}
		
	
}


