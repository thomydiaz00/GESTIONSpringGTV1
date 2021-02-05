package com.crud.CRUDSpring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.CRUDSpring.entity.Authority;
import com.crud.CRUDSpring.entity.User;
import com.crud.CRUDSpring.interfaceService.IfServiceAuthority;
import com.crud.CRUDSpring.interfaceService.IfServiceUser;
import com.crud.CRUDSpring.interfaces.interfaceUser;
import com.crud.CRUDSpring.service.UserDetailsServiceImpl;

@Controller
public class UserController {
	@Autowired
	interfaceUser userInterface;
	
	@Autowired
	IfServiceUser userService;
	@Autowired 
	IfServiceAuthority authority; 
	@Autowired
	UserDetailsServiceImpl userDetails;
	
	//Obtiene el usuario que está logeado y lo devuelve al Model
	@GetMapping("/admin/configuracion")
	public String config(Model model, Authentication authentication) {
		User User = (User) userInterface.findByUsername(authentication.getName());
		model.addAttribute("user", User);
		return "admin_config";
		
	}
	@GetMapping({"/admin/configuracion/agregar_usuario"} )
	public String agregarUsuario(Model model, @RequestParam(value="usernameRepeated", required=false) Optional<String> usernameRepeated) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.listarUser());
		model.addAttribute("roles", authority.listarAuthorities());
		if(usernameRepeated.isPresent()) {
			model.addAttribute("usernameRepeated", true);
			System.out.println("hay dup");
			return "agregar_usuario";

		}else {
			model.addAttribute("usernameRepeated", false);
			System.out.println("no hay dup");
			return "agregar_usuario";
		}
		
	}
	
	
	@PostMapping("/admin/configuracion/guardar_usuario")
	public String guardarUsuario(@Valid User user, RedirectAttributes redirectAttributes) {
			//User usernameUnique = userInterface.findByUsername(user.getUsername());
			String usname = user.getUsername();
			if(userInterface.findByUsername(usname) == null) {
				userService.guardarUser(user);
				return "admin_config";
			}else {
				redirectAttributes.addAttribute("usernameRepeated", true);
				return "redirect:../configuracion/agregar_usuario";
				
			}
	}
			
		
	
	
}
