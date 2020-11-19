package com.crud.CRUDSpring.interfaceService;
import com.crud.CRUDSpring.model.Clase;
import java.util.List;
import java.util.Optional;

public interface IfServiceClase {
	public List<Clase> listarClase();
	public Optional<Clase> clasePorId(int id);
	public int guardarClase(Clase clase);
	public void borrarClase(int id);
}
