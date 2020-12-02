package com.crud.CRUDSpring.controller;

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

@Controller
public class ProfesorController {
	
		@Autowired
		private IfServiceProfesor service;
		@Autowired
		private IfServiceClase servClase;
		
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
		@GetMapping("/admin/lista_profesores_completa")
		public String lista_profesores_completa(Model model) {
			List<Profesor> profesoresComp= service.listarProfesores();
			model.addAttribute("profesores", profesoresComp);
			return "lista_profesores_completa";
		}
		@PostMapping("/admin/save")
		public String save(@Valid Profesor p, Model model) {
			service.guardarProfesor(p);
			return "redirect:/admin/lista_profesores";
		}
		
		
		@GetMapping("/admin/editar_profesor/{id}")
		public String editar(@PathVariable int id, Model model) {		//Uso PathVariable para establecer id como parametro
			Optional<Profesor> profesor= service.profesorPorId(id);
			model.addAttribute("profesor", profesor);
			
			return "form_profesor";
			
		}
		@GetMapping("admin/delete_profesor/{idProf}")
		public String eliminar(@PathVariable int idProf, Model model) {
			
			service.borrarProfesor(idProf);
			return "redirect:/admin/lista_profesores";
		}
		
	
}

