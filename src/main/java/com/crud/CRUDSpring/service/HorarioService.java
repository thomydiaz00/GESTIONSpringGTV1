package com.crud.CRUDSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.interfaces.interfaceHorario;
import com.crud.CRUDSpring.model.Horario;

@Service
public class HorarioService implements IfServiceHorario {
	@Autowired 
	interfaceHorario data;

	@Override
	public List<Horario> listarHorarios() {
		
		return (List<Horario>)data.findAll();
	}

	@Override
	public Optional<Horario> HorarioPorId(int id) {
		
		return data.findById(id);
	}

	@Override
	public int guardarHorario(Horario horario) {
		int estado = 0;
		Horario h = data.save(horario);
		if(!h.equals(null)) {
			estado = 1;
		}
		return estado;
	}

	@Override
	public void borrarHorario(int id) {
		data.deleteById(id);
		
	}

}
