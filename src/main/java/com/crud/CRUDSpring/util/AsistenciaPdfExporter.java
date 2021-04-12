package com.crud.CRUDSpring.util;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;
import com.crud.CRUDSpring.model.RegistroDeAsistencia;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class AsistenciaPdfExporter {
	private Clase clase;
    private List<Asistencia> asistencias;
    private List<Asistencia> faltas;
    private List<Asistencia> todasLasFechas;
    Profesor profesor;
    
	Comparator ascendingOrder = new Comparator(){
		
		@Override
		public int compare(Object a, Object b) {
			if(((Asistencia) a).getFechaAsistencia().isBefore(((Asistencia) b).getFechaAsistencia())) return -1;
			else return 1;
		}

	};
    
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(6);

		Font titulo = FontFactory.getFont(FontFactory.HELVETICA);
		titulo.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Fecha", titulo));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Estado", titulo));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Registros para la Fecha", titulo));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		Font ausente = FontFactory.getFont(FontFactory.HELVETICA);
		ausente.setColor(Color.RED);
		Collections.sort(todasLasFechas, ascendingOrder);
		for (Asistencia asistencia : todasLasFechas) {
			table.addCell(asistencia.getFechaAsistencia().toString());
			agregarAsistencia(table, asistencia, ausente);
			agregarRegistros(table, asistencia, ausente);
			
		}
	}

	public void agregarRegistros(PdfPTable table, Asistencia asistencia, Font ausente) {
		PdfPTable registros = new PdfPTable(3); 
		registros.setWidths(new float[] { 1.0f, 2.0f, 1.0f });
 
		writeTableColumnHeader(registros, "Lugar");
		writeTableColumnHeader(registros, "Horario");
		writeTableColumnHeader(registros, "Estado");

		for(RegistroDeAsistencia registro : asistencia.getRegistrosDeAsistencia()){
			boolean pendiente = false;
			registros.addCell(registro.getIdRegistro().getHorario().getLugar());
			registros.addCell(agregarHorarios(registro.getIdRegistro().getHorario()));
			registros.addCell(parseToPresenteOrAusente(registro, asistencia));
			
			
		}
		table.addCell(registros);
		
		
	}
	private PdfPTable agregarHorarios(Horario horario){
		PdfPTable horarios= new PdfPTable(3);
		horarios.setWidths(new float[] { 1.0f, 1.0f, 1.0f });
		writeTableColumnHeader(horarios, "Dia");
		writeTableColumnHeader(horarios, "Inicio");
		writeTableColumnHeader(horarios, "Fin");
		horarios.addCell(horario.getDia().getDiaDeLaSemana());  
		horarios.addCell(horario.getHora_inicio());
		horarios.addCell(horario.getHora_fin());
		return horarios;
	}
	private String parseToPresenteOrAusente(RegistroDeAsistencia registro, Asistencia asistencia){
		LocalDate localCurrentDate = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca"))
				.toLocalDate();
		if(asistencia.isEstadoAsistencia()){
			return "presente";
		}else{
			int res = registro.getIdRegistro().getAsistencia().getFechaAsistencia().compareTo(localCurrentDate);
			if(res <= 0){
				if(registro.isEstado()){
					return "presente";
				}else{
					return "ausente";
				}
			}else{
				return ("-----------");
			}
		}
	} 

	public void agregarAsistencia(PdfPTable table, Asistencia asistencia, Font ausente) {
		if(asistencias.contains(asistencia)){
            table.addCell("Presente");
        }else if(faltas.contains(asistencia)){
			PdfPCell celda = new PdfPCell();
			Phrase phrase = new Phrase("Ausente", ausente);
            celda.setPhrase(phrase);
			table.addCell(celda);
        }else{
			table.addCell("-------------");

		}
	}

	private void writeTableColumnHeader(PdfPTable tablaClases, String nombreColumna) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		PdfPCell celda = new PdfPCell();
		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);

		Phrase frase = new Phrase(nombreColumna, font);
		celda.setPhrase(frase);
		tablaClases.addCell(celda);

	}
	private void observaciones(Document document){
		Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		titulo.setSize(12);
		titulo.setColor(Color.BLACK);
		Paragraph p = new Paragraph("Observaciones: ", titulo);
		Paragraph prof= new Paragraph("Profesor: ", titulo);
		prof.add(profesor.getNombreProf() + " " + profesor.getApellidoProf() + ", DNI: " + profesor.getDniProf());
		if(faltas.isEmpty()){
			p.add(" No se registraron faltas para este profesor en este mes.");
		}else{
			p.add(" Hubo un total de " + faltas.size() + " faltas en este mes.");
		}
		document.add(prof);
		document.add(p);

	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		Image deportesBaradero = Image.getInstance("src/main/resources/static/img/deportesPDF.jpg");
		deportesBaradero.setWidthPercentage(50);
		deportesBaradero.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(deportesBaradero);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String date = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate().format(formatter);
		Paragraph p = new Paragraph("Lista de Asistencias - " + clase.getNombreDep() + " - Hasta " + date.toString(), font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 1.0f, 6.0f });
		table.setSpacingBefore(10);
		writeTableHeader(table);
		writeTableData(table);
		observaciones(document);
		document.add(table);


		document.close();

	}


    
    public AsistenciaPdfExporter(Profesor profesor, Clase clase, List<Asistencia> todasLasFechas, List<Asistencia> asistencias, List<Asistencia> faltas) {
        this.clase = clase;
        this.todasLasFechas = todasLasFechas;
        this.asistencias = asistencias;
        this.faltas = faltas;
        this.profesor = profesor;
    }

    
}