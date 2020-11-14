package com.crud.CRUDSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfesorController {
@GetMapping({"/lista_profesores"})
	
	public String profesores() {
		return "lista_profesores";
	}

}
