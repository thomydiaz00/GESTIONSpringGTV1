package com.crud.CRUDSpring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.util.ProfesorPdfExporter;
import com.lowagie.text.DocumentException;

@Controller
public class PdfExportController {
	@Autowired
	private IfServiceProfesor service;
	@GetMapping ("admin/lista_profesores/export")
	public void exportarProfesores(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement;filename=profesores.pdf";
		
		response.setHeader(headerKey, headerValue);
		List<Profesor> listadoProfesores = service.listarProfesores();
		ProfesorPdfExporter  exporter = new ProfesorPdfExporter(listadoProfesores);
		exporter.export(response);
		
	}

}
