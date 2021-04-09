package com.crud.CRUDSpring.util;

import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.crud.CRUDSpring.model.Profesor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
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

public class ProfesorPdfExporter {
	private List<Profesor> listProfesores;

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(6);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Nombre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Apellido", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DNI", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Matricula", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Fecha nacimiento", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Telefono", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (Profesor profesor: listProfesores) {
			table.addCell(profesor.getNombreProf());
			table.addCell(profesor.getApellidoProf());
			table.addCell(String.valueOf(profesor.getDniProf()));
			table.addCell(profesor.getMatriculaProf());
			table.addCell(String.valueOf(profesor.getFechaNacProf()));
			table.addCell(profesor.getMatriculaProf());
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
		Paragraph p = new Paragraph("Lista de Profesores - " + date.toString(), font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.6f, 1.6f, 1.6f, 1.6f, 1.6f, 1.6f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}

	public ProfesorPdfExporter(List<Profesor> listProfesores) {
		this.listProfesores = listProfesores;
	}


}
