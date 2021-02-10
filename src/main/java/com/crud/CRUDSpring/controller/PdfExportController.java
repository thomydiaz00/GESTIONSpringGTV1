package com.crud.CRUDSpring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.util.ClasePdfExporter;
import com.crud.CRUDSpring.util.ProfesorPdfExporter;
import com.lowagie.text.DocumentException;

@Controller
public class PdfExportController {
	@Autowired
	private IfServiceProfesor service;
	@Autowired
	private IfServiceClase serviceClase;

	@GetMapping("admin/lista_profesores/export")
	public void exportarProfesores(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement;filename=profesores.pdf";

		response.setHeader(headerKey, headerValue);
		List<Profesor> listadoProfesores = service.listarProfesores();
		ProfesorPdfExporter exporter = new ProfesorPdfExporter(listadoProfesores);
		exporter.export(response);

	}

	@GetMapping("admin/lista_clases/export")
	public void exportarClases(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement;filename=Clase.pdf";

		response.setHeader(headerKey, headerValue);
		List<Clase> listadoClase = serviceClase.listarClase();
		ClasePdfExporter exporter = new ClasePdfExporter(listadoClase);
		exporter.export(response);

	}

}
