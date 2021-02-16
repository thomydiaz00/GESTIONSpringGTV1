package com.crud.CRUDSpring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.CRUDSpring.interfaceService.IfServiceHorario;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.Horario;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin("*")

public class ReactAppController {

    @Autowired
    interfaceProfesor interfaceProf;
    @Autowired
    IfServiceProfesor serviceProfesor;

    // Este metodo es el que usamos al enviar el form, evalua si el prof enviado
    // coincide con el dni
    @GetMapping("/all")
    public List<Profesor> getAll() {
        return serviceProfesor.listarProfesores();
    }

    @GetMapping("/horarios/{idProf}")
    public List<Horario> getHorarios(@PathVariable int id) {
        List<Clase> clases = serviceProfesor.profesorPorId(id).get().getClases();
        List<Horario> AllHorarios = new ArrayList<Horario>();
        for (Clase clase : clases) {
            for (Horario horario : clase.getHorarios()) {
                AllHorarios.add(horario);
            }
        }
        return AllHorarios;
    }

}
