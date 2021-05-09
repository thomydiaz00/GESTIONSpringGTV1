package com.crud.CRUDSpring.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class HorariosPdfExporter {
	private List<Clase> listClases;

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(6);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Nombre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Horarios", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Deporte", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Inicio de la Clase", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Finalización de la Clase", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		for (Clase clase : listClases) {
			table.addCell(clase.getNombreDep());
			agregarHorariosTable(table, clase);
			table.addCell(clase.getDeporte());
			table.addCell(clase.getFechaInicio().toString());
			table.addCell(clase.getFechaFin().toString());
		}
	}

	public PdfPTable agregarHorariosTable(PdfPTable tablaClases, Clase clase) {
		PdfPTable tablaHorario = new PdfPTable(3);
		tablaHorario.setWidthPercentage(100f);
		int indexHorario = clase.getHorarios().size();
		List<Horario> listaHorarios = clase.getHorarios();
		writeTableColumnHeader(tablaHorario, "Hora Inicio");
		writeTableColumnHeader(tablaHorario, "Hora Fin");
        writeTableColumnHeader(tablaHorario, "Lugar");


		if (indexHorario != 0) {
			for (int i = 0; i < indexHorario; i++) {
				tablaHorario.addCell(listaHorarios.get(i).getHora_inicio());
				tablaHorario.addCell(listaHorarios.get(i).getHora_fin());
                tablaHorario.addCell(listaHorarios.get(i).getLugar());
			}
		} else {
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
		Image deportesBaradero = Image.getInstance("src/main/resources/static/img/pdfheader.jpg");
		deportesBaradero.setWidthPercentage(50);
		deportesBaradero.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(deportesBaradero);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String date = new Date().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate().format(formatter);
		
        Paragraph p = new Paragraph("Lista de Horarios - " + date.toString(), font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 1.5f, 2.0f, 2.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}

	public HorariosPdfExporter(List<Clase> listClases) {
		this.listClases = listClases;
	}

}