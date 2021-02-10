package com.crud.CRUDSpring.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.CRUDSpring.interfaceService.*;
import com.crud.CRUDSpring.interfaces.*;
import com.crud.CRUDSpring.model.*;
import com.crud.CRUDSpring.service.*;
import com.crud.CRUDSpring.service.*;

import net.bytebuddy.asm.Advice.Local;

@Controller
public class AppController {

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
		return "profesor_login";

	}

	// -----------Declaro las interfaces que proveen los metodos CRUD (listById(),
	// etc) para usarlos en mi implementacion---

}
