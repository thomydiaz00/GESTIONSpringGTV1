package com.crud.CRUDSpring.interfaceService;
import com.crud.CRUDSpring.model.Persona;

import java.util.List;
import java.util.Optional;


/*interfaz para el servicio, a implementar
 * Aca declaro las funciones y metodos que
 * voy a utilizar para listar todos, listar por ID, Guardar
 * y Eliminar
 */
public interface IfServicePersona {
	public List<Persona> listar(String keyword);
	public Optional<Persona>listarId(int id);
	public int save(Persona p);
	public void delete(int id);
	

}

