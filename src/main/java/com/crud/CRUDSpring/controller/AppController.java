package com.crud.CRUDSpring.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT")); // It will set UTC timezone
		System.out.println("Spring boot application running in UTC timezone :" + new Date()); // It will print UTC
																								// timezone
	}

	@GetMapping({ "/", "/login" })
	public String index() {
		return "index";
	}

	// Si no existe el usuario, mando el status de error en la vista.
	@GetMapping("/login_profesor")
	public String profIndex(@RequestParam(value = "error", required = false) Optional<Boolean> noHayUsuario,
			Model model) {
		if (noHayUsuario.isPresent()) {
			model.addAttribute("error", true);
		}
		return "vistas_profesor/profesor_login";

	}

}
