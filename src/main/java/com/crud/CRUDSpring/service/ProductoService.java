package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.*;
import com.crud.CRUDSpring.interfaces.*;

import com.crud.CRUDSpring.model.*;
import com.crud.CRUDSpring.repository.*;

@Service
public class  ProductoService implements IfServiceProducto{

	@Autowired
	//creo algo de tipo iPersona para manejar las personas
	private interfaceProducto data; 
	@Autowired
	private ProductoRepository searchsv;
	
	//este metodo devuelve a todas las personas de la lista con findAll()
	

	//Devuelve persona que matchee con el id
	@Override
	public Optional<Producto> ProductolistarId(int idproducto) {
		return data.findById(idproducto);
 
		
	}
	
	/*
	 * Guarda la persona en la bd. persona (tipo Persona) guarda los datos guardados.
	 * la funcion devuelve  1 si persona no es null, devuelve 0 en caso contrario
	 */
	@Override
	public int saveProd(Producto p) {
		int res=0;
		Producto producto=data.save(p);
		if(!producto.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void deleteProd(int idproducto) {
		data.deleteById(idproducto);
		
	}

	@Override
	public List<Producto> listarProd(String busqueda) {
		
		if (busqueda != null) {
            return searchsv.search(busqueda);
        }else {
        	return (List<Producto>)data.findAll();
        	
        }
		
		
		
	}

	
	

}