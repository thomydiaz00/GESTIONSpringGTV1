package com.crud.CRUDSpring.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.crud.CRUDSpring.interfaceService.IfServiceClase;
import com.crud.CRUDSpring.interfaceService.IfServiceProfesor;
import com.crud.CRUDSpring.interfaces.interfaceAsistencia;
import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.util.AsistenciaPdfExporter;
import com.crud.CRUDSpring.util.ClasePdfExporter;
import com.crud.CRUDSpring.util.HorariosPdfExporter;
import com.crud.CRUDSpring.util.ProfesorPdfExporter;
import com.lowagie.text.DocumentException;

@Controller
public class PdfExportController {
	@Autowired
	private IfServiceProfesor service;
	@Autowired
	private IfServiceClase serviceClase;
	@Autowired
	private IfServiceProfesor serviceProfesor;
	@Autowired
	private interfaceAsistencia interfaceAsis;

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
	// @GetMapping("admin/lista_horarios/export")
	// public void exportarProfesores(HttpServletResponse response, @int clase) throws DocumentException, IOException {
	// 	response.setContentType("application/pdf");
	// 	String headerKey = "Content-Disposition";
	// 	String headerValue = "attachement;filename=profesores.pdf";

	// 	response.setHeader(headerKey, headerValue);
	// 	List<Profesor> listadoProfesores = service.listarProfesores();
	// 	ProfesorPdfExporter exporter = new ProfesorPdfExporter(listadoProfesores);
	// 	exporter.export(response);

	// }


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

	@GetMapping("admin/lista_horarios/export")
	public void exportarHorarios(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement;filename=Horarios.pdf";

		response.setHeader(headerKey, headerValue);
		List<Clase> listadoClase = serviceClase.listarClase();
		HorariosPdfExporter exporter = new HorariosPdfExporter(listadoClase);
		exporter.export(response);

	}

	@GetMapping("admin/lista_asistencias/export/{profesor}/{clase}/{mes}/{anio}")
	public void exportarAsistencias(HttpServletResponse response, @PathVariable(value = "profesor") int profesor, @PathVariable(value = "clase") int clase, @PathVariable(value = "mes") int mes, @PathVariable(value = "anio") int anio  ) throws DocumentException, IOException {
		
		Optional<Clase> c = serviceClase.clasePorId(clase);
		Optional<Profesor> p = serviceProfesor.profesorPorId(profesor);
		List<Asistencia> asistenciasCompletas = new ArrayList<Asistencia>();
		List<Asistencia> faltas = new ArrayList<Asistencia>();
		List<Asistencia> asistenciasPorMes = new ArrayList<Asistencia>();
		List<Asistencia> asistencias = interfaceAsis.findByClaseAndProfesor(c.get(), p.get());
		LocalDate localCurrentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();

		for(Asistencia asistencia : asistencias){
			LocalDate fechaDeAsistencia = asistencia.getFechaAsistencia();
			if(fechaDeAsistencia.getMonthValue() == mes){
				asistenciasPorMes.add(asistencia);
			}
		}

		for(Asistencia asistencia: asistenciasPorMes){	
			LocalDate fechaDeAsistencia = asistencia.getFechaAsistencia();
			int res = fechaDeAsistencia.compareTo(localCurrentDate);
			if((res <= 0) && (fechaDeAsistencia.getYear() == anio)){
				if(asistencia.isEstadoAsistencia()){
					asistenciasCompletas.add(asistencia);
				}else{
					faltas.add(asistencia);
				}
			}
		}
		System.out.println( "cantidad de asistencias: " +asistenciasCompletas.size());
		System.out.println("cantidad de faltas: " +faltas.size());

		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement;filename=asistencias-"+ p.get().getApellidoProf().toLowerCase()+ "-"+ mes + "-" + anio + ".pdf";

		response.setHeader(headerKey, headerValue);
		AsistenciaPdfExporter exporter = new AsistenciaPdfExporter(p.get(), c.get(), asistenciasPorMes, asistenciasCompletas, faltas);
		exporter.export(response);
		

		



		// interfaceAsis.findByFechaAsistenciaInAndProfesorInAndClase(date, profesor, clase);

		// response.setContentType("application/pdf");
		// String headerKey = "Content-Disposition";
		// String headerValue = "attachement;filename=Clase.pdf";

		// response.setHeader(headerKey, headerValue);
		// List<Clase> listadoClase = serviceClase.listarClase();
		// HorariosPdfExporter exporter = new HorariosPdfExporter(listadoClase);
		// exporter.export(response);

	}

}
