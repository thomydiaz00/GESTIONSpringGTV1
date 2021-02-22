package com.crud.CRUDSpring.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Persona;
import com.crud.CRUDSpring.model.Profesor;

@Controller
public class ClaseController {

	@Autowired
	private IfServiceClase service;

	@Autowired
	private IfServiceHorario serviceHorario;

	@GetMapping("/admin/lista_clases")
	public String ListarClases(Model model) {
		List<Clase> clases = service.listarClase();
		model.addAttribute("clases", clases);
		model.addAttribute("clase", new Clase());
		return "lista_clases";
	}

	@GetMapping("/admin/lista_clases/nuevo")
	public String agregarClase(Model model) {
		model.addAttribute("clase", new Clase());
		return "form_clase";

	}

	@PostMapping("/admin/save_clase")
	public String saveClase(@Valid Clase clase, Model model) {
		service.guardarClase(clase);
		return "redirect:/admin/lista_clases";
	}

	@GetMapping("/admin/editar_clase/{id}")
	public String editarClase(@PathVariable int id, Model model) { // Uso PathVariable para establecer id como parametro
		Optional<Clase> clase = service.clasePorId(id);
		model.addAttribute("clase", clase);
		return "form_clase";

	}

	@GetMapping("admin/delete_clase/{id}")
	public String eliminar(@PathVariable int id, Model model) {
		service.borrarClase(id);
		return "redirect:/admin/lista_clases";
	}

}
