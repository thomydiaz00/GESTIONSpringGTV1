package com.crud.CRUDSpring.util;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.crud.CRUDSpring.model.Clase;
import com.crud.CRUDSpring.model.Horario;
import com.crud.CRUDSpring.model.Profesor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import com.lowagie.text.*;

public class ClasePdfExporter {
	private List<Clase> listClases;

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(6);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Nombre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Deporte", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Profesores", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Horarios", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Inicio", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Fin", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (Clase clase : listClases) {
			table.addCell(setFont(clase.getNombreDep()));
			table.addCell(setFont(clase.getDeporte()));
			agregarProfesorTable(table, clase);
			agregarHorariosTable(table, clase);
			table.addCell(setFont(clase.getFechaInicio().toString()));
			table.addCell(setFont(clase.getFechaFin().toString()));
		}
	}

	public PdfPTable agregarProfesorTable(PdfPTable tablaClases, Clase clase) {
		PdfPTable tablaProfesor = new PdfPTable(2);
		tablaProfesor.setWidthPercentage(100f);
		tablaProfesor.setWidths(new float[] { 2.4f, 2.4f});

		int indexProf = clase.getProfesores().size();
		List<Profesor> listaProfesores = clase.getProfesores();
		writeTableColumnHeader(tablaProfesor, "Nombre");
		writeTableColumnHeader(tablaProfesor, "Apellido");
		if (indexProf != 0) {
			for (int i = 0; i < indexProf; i++) {
				tablaProfesor.addCell(setFont(listaProfesores.get(i).getNombreProf()));
				tablaProfesor.addCell(setFont(listaProfesores.get(i).getApellidoProf()));
			}
		} else {
			tablaProfesor.addCell("-----");
			tablaProfesor.addCell("-----");
		}
		tablaClases.addCell(tablaProfesor);
		return tablaClases;

	}
	private Phrase setFont(String x){
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setSize(9);
		return new Phrase(x, font);


	}
	public PdfPTable agregarHorariosTable(PdfPTable tablaClases, Clase clase) {
		PdfPTable tablaHorario = new PdfPTable(4);
		tablaHorario.setWidthPercentage(100f);
		tablaHorario.setWidths(new float[] { 2.4f, 2.0f, 2.0f, 4.0f});

		int indexHorario = clase.getHorarios().size();
		List<Horario> listaHorarios = clase.getHorarios();
		tablaHorario.setWidthPercentage(100f);
		writeTableColumnHeader(tablaHorario, "Dia");
		writeTableColumnHeader(tablaHorario, "Hora Inicio");
		writeTableColumnHeader(tablaHorario, "Hora Fin");
        writeTableColumnHeader(tablaHorario, "Lugar");


		if (indexHorario != 0) {
			for (int i = 0; i < indexHorario; i++) {
				tablaHorario.addCell(setFont(listaHorarios.get(i).getDia().getDiaDeLaSemana()));
				tablaHorario.addCell(setFont(listaHorarios.get(i).getHora_inicio()));
				tablaHorario.addCell(setFont(listaHorarios.get(i).getHora_fin()));
                tablaHorario.addCell(setFont(listaHorarios.get(i).getLugar()));
			}
		} else {
			tablaHorario.addCell("-----");
			tablaHorario.addCell("-----");
            tablaHorario.addCell("-----");
			tablaHorario.addCell("-----");

		}

		tablaClases.addCell(tablaHorario);
		return tablaClases;

	}

	private void writeTableColumnHeader(PdfPTable tablaClases, String nombreColumna) {
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		font.setSize(9);
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
		Paragraph p = new Paragraph("Lista de Clases - " + date.toString(), font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 2.0f, 2.0f, 3.5f, 7.0f, 2.0f, 2.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}

	public ClasePdfExporter(List<Clase> listClases) {
		this.listClases = listClases;
	}

}
// private List<Clase> listadoClases;

// public ClasePdfExporter(List<Clase> listadoClases) {
// super();
// this.listadoClases = listadoClases;
// }

// // metodo para escribir los nombres de las columnas de las tablas

// /*
// * Metodo para escribir datos en la tabla, itera por cada profesor Y agrega la
// * Si hay profesores, imprime una fila con los datos, sino imprime "----"
// */
// private void writeTableData(PdfPTable tablaClases) {

// tablaClases.addCell(tablaProfesor);

// // hay que ver esto porque viene de otra clase
// // tablaClases.addCell(clase.getAsistencias());
// // hay que ver esto porque viene de otra clase
// // tablaClases.addCell(clase.getProfesores());
// });

// }

// public void export(HttpServletResponse response) throws DocumentException,
// IOException {
// Document document = new Document(PageSize.A4);
// PdfWriter.getInstance(document, response.getOutputStream());
// document.open();
// PdfPTable tablaClases = new PdfPTable(3);
// tablaClases.setWidths(new float[] { 1, 1, 2 });

// writeTableHeader(tablaClases, "Nombre de la Clase");
// writeTableHeader(tablaClases, "Deporte");
// writeTableHeader(tablaClases, "Profesores a cargo");

// writeTableData(tablaClases);

// // document.add(tablaProfesores);
// // document.addTitle("ss");
// document.add(tablaClases);
// document.close();
// }
