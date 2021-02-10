package com.crud.CRUDSpring.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.Horario;

@Controller

public class ProfesorController {

	@Autowired
	interfaceProfesor interfaceProf;
	@Autowired
	IfServiceProfesor serviceProfesor;

	// Este metodo es el que usamos al enviar el form, evalua si el prof enviado
	// coincide con el dni
	@RequestMapping(value = "/profesor/redirect", method = { RequestMethod.POST, RequestMethod.GET })
	public String checkSiExisteProfesor(@RequestParam("dniProfesor") int dni, RedirectAttributes ra) {
		try {
			Profesor profesor = interfaceProf.findByDniProf(dni).get();
			ra.addAttribute("profesor", profesor);
			return "redirect:/profesor/clases";

		} catch (Exception NoSuchElemenException) {
			ra.addAttribute("error", true);
			return "redirect:/login_profesor";

		}

	}

	@GetMapping("/profesor/clases")
	public String clasesProfesor(@ModelAttribute Profesor profesor, Model model,
			@RequestParam(value = "noHayHorarios", required = false) Optional<Boolean> noHayHorarios) {
		Iterable<Clase> clases = profesor.getClases(); // ver para hacer la relacion de
		System.out.println("Refeshed");
		model.addAttribute("profesor", profesor);
		model.addAttribute("clases", clases);
		return "logedin_profesor_clase";
	}

	@GetMapping("/profesor/horarios/{dniProf}")
	public String ConsultarHorariosProfesor(@PathVariable int dniProf, @PathVariable int idHorario,
			RedirectAttributes ra) {
		try {
			Optional<Profesor> profesor = interfaceProf.findByDniProf(dniProf);
			List<Clase> clases = profesor.get().getClases();

		} catch (Exception NoSuchElemenException) {
			ra.addAttribute("errorNoHayHorarios", true);
			return "redirect:/profesor/clases ";

		}

		return "index";
	}

}
