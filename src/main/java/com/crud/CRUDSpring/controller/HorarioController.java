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
import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;

@Controller
public class HorarioController {
	@Autowired
	IfServiceHorario serviceHorario;
	@Autowired
	IfServiceClase serviceClase;
	
	@GetMapping("/admin/lista_horarios/{id}")
	public String listaHorarios(@PathVariable int id, Model model) {
		Clase cla = serviceClase.clasePorId(id).orElse(null);
		model.addAttribute("idclase", id);
		model.addAttribute("clase", cla.getHorarios());
		return "lista_horarios";
		
	}
	@GetMapping("/admin/agregar_horario/{id}")
	public String agregarHorario(@PathVariable int id, Model model) {		//Uso PathVariable para establecer id como parametro
		model.addAttribute("idclase", id);
		model.addAttribute("horario", new Horario());		
		return "form_horario";
		
	}
	
	@GetMapping("/admin/eliminar_horario/{id}")
	public String eliminarHorario(@PathVariable int id) {
		serviceHorario.borrarHorario(id);
		return "redirect:/admin/lista_horarios/{id}";
	}
	@PostMapping("/admin/save_horario/{id}")
	public String saveHorario(@PathVariable int id,@Valid Horario horario, Model model) {
		Clase clase = serviceClase.clasePorId(id).orElse(null);
		horario.setClase(clase);
		clase.agregarHorario(horario);
		serviceClase.guardarClase(clase);
		serviceHorario.guardarHorario(horario);
		System.out.println("guardado");
		return "redirect:/admin/lista_horarios/{id}";
	}
	@GetMapping("/admin/editar_horario/{id}/{idclase}")
	public String editar(@PathVariable int id, Model model,@PathVariable int idclase) {		//Uso PathVariable para establecer id como parametro
		Horario horario = serviceHorario.HorarioPorId(id).orElse(null);
		model.addAttribute("idclase",idclase);
		model.addAttribute("horario", horario);
		
		return "form_horario";
		
	}

}
