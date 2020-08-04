package com.crud.CRUDSpring.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.CRUDSpring.interfaceService.*;
import com.crud.CRUDSpring.model.*;

@Controller
public class ProductoController {

	@Autowired
	private IfServiceProducto serviceprod;
	
	/*
	 *  el metodo listar() de service devuelve una lista, personas es una lista pivote
	 *  agrego los datos al modelo y devuelvo la pagina
	 */
	@GetMapping("/admin/listarproductos")
	public String Home(Model model, @Param("busqueda") String busqueda) {
		List<Producto> productos= serviceprod.listarProd(busqueda);		
		model.addAttribute("productos", productos);
		model.addAttribute("busqueda", busqueda); 
		return "listaProductos";
	}
	
	@GetMapping("/admin/listarproductos/new")
	public String agregarProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "formProducto";
		
	}
	/*
	 * Metodo para guardar, toma como parametro una persona p, utiliza la interfaz service (tipo ifServicePersona) para guardar
	 */
	@PostMapping("/admin/saveproducto")
	public String saveProducto(@Valid Producto p, Model model) {
		serviceprod.saveProd(p);
		return "redirect:/admin/listarproductos";
	}
	
	/*
	 * Metodo para editar. Obtiene el id pasado como parametro de la lista para una persona. Luego retorna
	 * el formulario con los datos que corresponden a la persona a editar
	 */
	@GetMapping("/admin/editarproducto/{idproducto}")
	public String editaProd(@PathVariable int idproducto, Model model) {		//Uso PathVariable para establecer id como parametro
		Optional<Producto> producto= serviceprod.ProductolistarId(idproducto);
		model.addAttribute("producto", producto);
		
		return "formProducto";
		
	}
	@GetMapping("admin/deleteproducto/{idproducto}")
	public String eliminarProd(@PathVariable int idproducto, Model model) {
		serviceprod.deleteProd(idproducto);
		return "redirect:/admin/listarproductos";
	}
	
	@GetMapping("/user/listarproductos")
	public String UserHome(Model model, @Param("busqueda") String busqueda) {
		List<Producto> productos= serviceprod.listarProd(busqueda);		
		model.addAttribute("productos", productos);
		model.addAttribute("busqueda", busqueda); 
		return "userViewProductos";
	}
}
