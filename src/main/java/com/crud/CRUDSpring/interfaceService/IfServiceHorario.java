package com.crud.CRUDSpring.interfaceService;

import java.util.List;
import java.util.Optional;

import com.crud.CRUDSpring.model.Horario;

public interface IfServiceHorario {
	public List<Horario> listarHorarios();
	public Optional<Horario> HorarioPorId(int id);
	public int guardarHorario(Horario horario);
	public void borrarHorario(int id);

}
