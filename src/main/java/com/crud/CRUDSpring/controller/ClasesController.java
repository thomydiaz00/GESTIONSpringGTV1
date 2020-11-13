package com.crud.CRUDSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClasesController {
@GetMapping({"/admin/lista_clases"})
	
	public String clases() {
		return "lista_clases";
	}
	
}


