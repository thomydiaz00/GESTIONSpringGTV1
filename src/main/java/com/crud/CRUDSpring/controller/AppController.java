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
	
	


	
	@GetMapping({"/","/login"})
	
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	//-----------Declaro las interfaces que proveen los metodos CRUD (listById(), etc) para usarlos en mi implementacion---

	@Autowired
	private IfServicePersona service;
	
	@Autowired
	private IfServicePersona adminService;
	
	
	//----------Controllers-Admin---------------------
	
	/*
	 * model es instanciado por Spring, se "inyecta" al metodo del model
	 * que corresponda para la consulta.
	 * entonces, agrega persona a personas y pasa el parametro keyword
	 * que luego se usara en la barra de busqueda
	 * 
	 * Permite acceder a la vista con "${personas}" y "${keyword}" 
	 */
	
	@GetMapping("/admin/listar")
	public String Home(Model model, @Param("keyword") String keyword) {
        
        List<Persona> personas= service.listar(keyword);
        model.addAttribute("personas", personas);
        model.addAttribute("keyword", keyword); 
       
		return "lista";
    }

	
	/*
	 * Agrega nueva instancia de persona en "personas"
	 * los datos de la nueva persona van a ingresarse en "formulario"
	 */
	@GetMapping("/admin/listar/new")
	public String agregar(Model model) {
		model.addAttribute("persona", new Persona());
		return "formulario";
		
	}
	/*
	 * Metodo para guardar, metodo save de "service" para guardar persona es heredado de CrudRepo
	 * Al terminar redirecciona a "/admin/listar" para que ver los cambios
	 */
	@PostMapping("/admin/save")
	public String save(@Valid Persona p, Model model) {
		service.save(p);
		return "redirect:/admin/listar";
	}
	
	/*
	 * Metodo para editar. Obtiene el id pasado como parametro de la lista para una persona, que va
	 * a ser una URL. Luego retorna el formulario con los datos que corresponden a la persona a editar
	 */
	@GetMapping("/admin/editar/{id}")
	public String editar(@PathVariable int id, Model model) {		//Uso PathVariable para establecer id como parametro
		Optional<Persona> persona= service.listarId(id);
		model.addAttribute("persona", persona);
		
		return "editar_cliente";
		
	}
	@GetMapping("admin/delete/{id}")
	public String eliminar(@PathVariable int id, Model model) {
		service.delete(id);
		return "redirect:/admin/listar";
	}
	
	
	//-------------Controllers-Usuarios------------------
	@GetMapping("/user/listar")
	public String userHome(Model model, @Param("keyword") String keyword) {
        
        List<Persona> personas= service.listar(keyword);
        model.addAttribute("personas", personas);
        model.addAttribute("keyword", keyword); 
       
		return "userViewClientes";
    }
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }
	
	
}

