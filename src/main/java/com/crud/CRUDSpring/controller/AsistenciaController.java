package com.crud.CRUDSpring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;

@Controller	

public class AsistenciaController {

	@Autowired
	IfServiceProfesor serviceProfesor;
	@Autowired interfaceProfesor interfaceProf;

	@RequestMapping(value="/profesor/redirect", method = {RequestMethod.POST,  RequestMethod.GET})
	public String checkSiExisteProfesor(@RequestParam("dniProfesor") int dni, RedirectAttributes ra) {
		try {
			Profesor profesor = interfaceProf.findByDniProf(dni).get();
			ra.addFlashAttribute("profesor", profesor );
			return "redirect:/profesor/clases";
			
		}catch(Exception NoSuchElemenException) {
			ra.addAttribute("error", true);
			return "redirect:/login_profesor";
			
		}
		
	}
	@GetMapping("/profesor/clases")
		public String clasesProfesor(@ModelAttribute Profesor profesor, Model model){
			System.out.println(profesor.getDniProf() + profesor.getApellidoProf());
			List<Clase> clases = profesor.getClases();
			model.addAttribute("clases",clases);
			return "logedin_profesor_clase";
		}
		
	
	
	
	
	
}
