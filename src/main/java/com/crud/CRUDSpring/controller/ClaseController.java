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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceDia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.DiaDePractica;

@Controller
public class ClaseController {

	@Autowired
	private IfServiceClase service;

	@Autowired
	private IfServiceDia serviceDia;

	@GetMapping("/admin/lista_clases")
	public String ListarClases(Model model) {

		List<Clase> clases = service.listarClase();
		LocalDate date = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		model.addAttribute("date", date);
		model.addAttribute("clases", clases);
		model.addAttribute("clase", new Clase());
		model.addAttribute("dias", serviceDia.listarDiaDePractica());
		return "listas/lista_clases";
	}

	@GetMapping("/admin/lista_clases/nuevo")
	public String agregarClase(Model model) {
		model.addAttribute("clase", new Clase());
		return "forms/form_clase";

	}

	@PostMapping("/admin/save_clase")
	public String saveClase(@Valid Clase clase, Model model, RedirectAttributes ra) {
		if(clase.getFechaInicio().compareTo(clase.getFechaFin()) > 0){
			ra.addFlashAttribute("mensajeClase", "La fecha de fin de la clase debe ser menor a la de inicio");
		}else{
			service.guardarClase(clase);
		}
		return "redirect:/admin/lista_clases";
	}

	@GetMapping("/admin/editar_clase/{id}")
	public String editarClase(@PathVariable int id, Model model) { // Uso PathVariable para establecer id como parametro
		Optional<Clase> clase = service.clasePorId(id);
		List<DiaDePractica> dias = serviceDia.listarDiaDePractica();
		model.addAttribute("clase", clase);
		model.addAttribute("dias", dias);

		return "forms/form_clase";

	}

	@GetMapping("admin/delete_clase/{id}")
	@ResponseBody
	public String eliminar(@PathVariable int id, Model model) {
		int response = service.borrarClase(id);
		return Integer.toString(response);
	}

}
