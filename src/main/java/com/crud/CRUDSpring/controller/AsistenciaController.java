package com.crud.CRUDSpring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.crud.CRUDSpring.model.Clase;

@Controller	

public class AsistenciaController {

	

	@GetMapping("/admin/asistencia")
	public String listarAsistencia() {
		return "asistencia";
	}
	
	
	
	
}
