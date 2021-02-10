package com.crud.CRUDSpring.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Profesor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ClasePdfExporter {
	private List<Clase> listadoClases;

	public ClasePdfExporter(List<Clase> listadoClases) {
		super();
		this.listadoClases = listadoClases;
	}

	// metodo para escribir los nombres de las columnas de las tablas
	private void writeTableHeader(PdfPTable tablaClases, String nombreColumna) {
		PdfPCell celda = new PdfPCell();
		Phrase frase = new Phrase(nombreColumna);
		celda.setPhrase(frase);
		tablaClases.addCell(celda);

	}

	/*
	 * Metodo para escribir datos en la tabla, itera por cada profesor Y agrega la
	 * Si hay profesores, imprime una fila con los datos, sino imprime "----"
	 */
	private void writeTableData(PdfPTable tablaClases) {

		listadoClases.forEach(clase -> {
			tablaClases.addCell(clase.getNombreDep());
			tablaClases.addCell(clase.getDeporte());
			int indexProf = clase.getProfesores().size();
			List<Profesor> listaProfesores = clase.getProfesores();
			PdfPTable tablaProfesor = new PdfPTable(2);
			writeTableHeader(tablaProfesor, "Nombre");
			writeTableHeader(tablaProfesor, "Apellido");
			if (indexProf != 0) {
				for (int i = 0; i < indexProf; i++) {
					tablaProfesor.addCell(listaProfesores.get(i).getNombreProf());
					tablaProfesor.addCell(listaProfesores.get(i).getApellidoProf());
				}
			} else {
				tablaProfesor.addCell("-----");
				tablaProfesor.addCell("-----");
			}
			tablaClases.addCell(tablaProfesor);

			// hay que ver esto porque viene de otra clase
			// tablaClases.addCell(clase.getAsistencias());
			// hay que ver esto porque viene de otra clase
			// tablaClases.addCell(clase.getProfesores());
		});

	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		PdfPTable tablaClases = new PdfPTable(3);
		tablaClases.setWidths(new float[] { 1, 1, 2 });

		writeTableHeader(tablaClases, "Nombre de la Clase");
		writeTableHeader(tablaClases, "Deporte");
		writeTableHeader(tablaClases, "Profesores a cargo");

		writeTableData(tablaClases);

		// document.add(tablaProfesores);
		// document.addTitle("ss");
		document.add(tablaClases);
		document.close();
	}

}