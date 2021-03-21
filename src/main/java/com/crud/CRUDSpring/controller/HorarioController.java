package com.crud.CRUDSpring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceDia;
import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.interfaces.interfaceClase;
import com.crud.CRUDSpring.interfaces.interfaceHorario;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;

@Controller
public class HorarioController {
	@Autowired
	IfServiceHorario serviceHorario;
	@Autowired
	IfServiceClase serviceClase;
	@Autowired
	IfServiceDia serviceDia;

	@GetMapping("/admin/lista_horarios/{id}")
	public String listaHorarios(@PathVariable int id, Model model) {
		Clase clase = serviceClase.clasePorId(id).orElse(new Clase());
		model.addAttribute("nombreClase", clase.getNombreDep());
		model.addAttribute("idclase", id);
		model.addAttribute("horarios", clase.getHorarios());
		return "lista_horarios";

	}

	@GetMapping("/admin/agregar_horario/{id}")
	public String agregarHorario(@PathVariable int id, Model model) {
		Clase clase = serviceClase.clasePorId(id).get();
		model.addAttribute("idclase", id);
		model.addAttribute("horario", new Horario());
		model.addAttribute("dias", clase.getDias());
		return "form_horario";

	}

	@GetMapping("/admin/eliminar_horario/{id}")
	public String eliminarHorario(@PathVariable int id) {
		// Clase clase = serviceClase.clasePorId(idClase).get();
		// Horario horario = serviceHorario.HorarioPorId(idHorario).get();
		// clase.getHorarios().remove(horario);
		// serviceClase.guardarClase(clase);
		// System.out.println(horario.getLugar());

		serviceHorario.borrarHorario(id);

		// serviceHorario.borrarHorario(id);

		return "redirect:/admin/lista_horarios/{id}";
	}

	@PostMapping("/admin/save_horario/{id}")
	public String saveHorario(@PathVariable int id, @Valid Horario horario, Model model) {
		serviceHorario.guardarHorario(horario);
		System.out.println("Horario guardado");
		return "redirect:/admin/lista_horarios/{id}";
	}

	@GetMapping("/admin/editar_horario/{id}/{idclase}")
	public String editar(@PathVariable int id, Model model, @PathVariable int idclase) {
		Optional<Horario> horario = serviceHorario.HorarioPorId(id);
		Clase clase = serviceClase.clasePorId(idclase).get();
		model.addAttribute("idclase", idclase);
		model.addAttribute("horario", horario);
		model.addAttribute("dias", clase.getDias());

		return "form_horario";

	}

}
