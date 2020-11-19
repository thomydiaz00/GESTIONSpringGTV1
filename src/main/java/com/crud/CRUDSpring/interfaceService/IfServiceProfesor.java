package com.crud.CRUDSpring.interfaceService;
import com.crud.CRUDSpring.model.Profesor;
import java.util.List;
import java.util.Optional;

public interface IfServiceProfesor {
	public List<Profesor> listarProfesores();
	public Optional<Profesor> profesorPorId(int id);
	public int guardarProfesor(Profesor p);
	public void borrarProfesor(int id);
}
