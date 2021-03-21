package com.crud.CRUDSpring.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.crud.CRUDSpring.model.Profesor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ProfesorPdfExporter {
	private List<Profesor> listadoProfesores;

	public ProfesorPdfExporter(List<Profesor> listadoProfesores) {
		super();
		this.listadoProfesores = listadoProfesores;
	}

	// metodo para escribir los nombres de las columnas de las tablas
	private void writeTableHeader(PdfPTable tablaProfesores, String nombreColumna) {

		PdfPCell celda = new PdfPCell();
		Phrase frase = new Phrase(nombreColumna);
		celda.setPhrase(frase);
		tablaProfesores.addCell(celda);

	}

	/*
	 * Metodo para escribir datos en la tabla, itera por cada profesor Y agrega la
	 * columna correspondiente
	 */
	private void writeTableData(PdfPTable tablaProfesores) {
		listadoProfesores.forEach(profesor -> {
			tablaProfesores.addCell(profesor.getApellidoProf());
			tablaProfesores.addCell(profesor.getNombreProf());
			tablaProfesores.addCell(String.valueOf(profesor.getDniProf()));
			tablaProfesores.addCell(profesor.getMatriculaProf());
			tablaProfesores.addCell(String.valueOf(profesor.getFechaNacProf()));
			tablaProfesores.addCell(String.valueOf(profesor.getTelefonoProf()));

		});

	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		PdfPTable tablaProfesores = new PdfPTable(6);

		writeTableHeader(tablaProfesores, "Apellido");
		writeTableHeader(tablaProfesores, "Nombre");
		writeTableHeader(tablaProfesores, "DNI");
		writeTableHeader(tablaProfesores, "Matricula");
		writeTableHeader(tablaProfesores, "Fecha Nacimiento");
		writeTableHeader(tablaProfesores, "Telefono");

		writeTableData(tablaProfesores);

		// document.add(tablaProfesores);
		// document.addTitle("ss");
		document.add(tablaProfesores);
		document.close();
	}

}
