package com.crud.CRUDSpring.service;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaces.*;
import com.crud.CRUDSpring.interfaceService.*;
import com.crud.CRUDSpring.model.*;
import com.crud.CRUDSpring.service.*;
/*
 * Implementa la IfServPers que contiene los metodos a implementar
 */
@Service
public class PersonaService implements IfServicePersona {

	//-------Declaracion de interfaces y repo para la busqueda----
	@Autowired
	
	private interfacePersona data; 
	
	
	/*
	 * Implementacion metodo de busqueda. Si se pasa alguna keyword (parametro para busqueda)
	 * va a ejecutar el query, del cual los resultados que coincidan se van a guardar en una lista
	 * de personas. En caso de que no se pase ninguna keyword, simplemente va a listar a todos los clientes
	 */
	@Override
	public List<Persona> listar() {
        	return (List<Persona>)data.findAll();
	}

	/*
	 * Metodo para listar por ID. Si hay algún cliente que coincida con el id que se pasa como param
	 * utilizo el metodo de CRUD findById para buscarlo y retornarlo
	 */
	@Override
	public Optional<Persona> listarId(int id) {
		return data.findById(id);
		
	}
	
	/*
	 * Utiliza la funcion de Crud "save" para guardar la nueva persona. 
	 * si persona es null retorna 1, caso contrario retorna 0
	 */
	@Override
	public int save(Persona p) {
		int res=0;
		Persona persona=data.save(p);
		if(!persona.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
		
	}

	@Override
	public int updatePersona(Persona p) {
		int res =0;
		
		return res;
	}

	
}
	

	