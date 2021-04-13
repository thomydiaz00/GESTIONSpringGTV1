package com.crud.CRUDSpring.controller;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

	// Obtiene el usuario que está logeado y lo devuelve al Model

	@GetMapping("/admin/configuracion/ajustes")
	public String config(Model model, Authentication authentication) {
		Optional<User> user = userInterface.findByUsername(authentication.getName());
		System.out.println("current user : " + user.get().getUsername());
		if(user.isPresent()){
			model.addAttribute("user", user.get());
		}
		return "admin_config";
		
	}
	@GetMapping({ "/admin/configuracion" })
	public String agregarUsuario(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", userService.listarUser());
		model.addAttribute("roles", authority.listarAuthorities());
		return "agregar_usuario";

		

	}
	@PostMapping("/admin/configuracion/guardar_usuario")
	public String guardarUsuario(@Valid User user, RedirectAttributes redirectAttributes) {
		// User usernameUnique = userInterface.findByUsername(user.getUsername());
		String usname = user.getUsername();
		Optional<User> usuarioExistente = userInterface.findByUsername(usname);
		if (usuarioExistente.isPresent()) {
			System.out.println("usuario reperitdo");
			if(user.getUsername().equals(usuarioExistente.get().getUsername()) && user.getId() != usuarioExistente.get().getId()){
				redirectAttributes.addFlashAttribute("usuarioRepetido", "Ya existe un usuario con el nombre \""+usname+"\"");
			}
		}else{
			userService.guardarUser(user);
		}
		return "redirect:/admin/configuracion";

	}
	@PostMapping("/admin/configuracion/guardar_usuario_editado")
	public String editarUsuario(@Valid User user, RedirectAttributes redirectAttributes) {
		// User usernameUnique = userInterface.findByUsername(user.getUsername());
		String usname = user.getUsername();
		Optional<User> usuarioExistente = userInterface.findByUsername(usname);
		if (usuarioExistente.isPresent()) {
			System.out.println("usuario reperitdo");
			if(user.getUsername().equals(usuarioExistente.get().getUsername()) && user.getId() != usuarioExistente.get().getId()){
				redirectAttributes.addFlashAttribute("usuarioRepetido", "Ya existe un usuario con el nombre \""+usname+"\"");
			}
		}else{
			userService.guardarUser(user);
		}

		return "redirect:/admin/configuracion";

	}
	@GetMapping("/admin/eliminar_usuario/{idUsuario}")
	public String eliminarUsuario(@PathVariable(value="idUsuario") int idUsuario){
		userService.borrarUser(idUsuario);
		return "redirect:/admin/configuracion";
	}
	@GetMapping("/admin/editar_usuario/{idUsuario}")
	public String editarUsuario(@PathVariable(value = "idUsuario") int idUsuario, Model model){
		Optional<User> user = userService.UserPorId(idUsuario);
		model.addAttribute("user", user);

		return "forms/form_user";

	}

}
