package com.crud.CRUDSpring.util;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.crud.CRUDSpring.model.Asistencia;
import com.crud.CRUDSpring.model.Clase;
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

public class AsistenciaPdfExporter {
	private Clase clase;
    private List<Asistencia> asistencias;
    private List<Asistencia> faltas;
    private List<Asistencia> todasLasFechas;
    Profesor profesor;
    
    
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
	}

	private void writeTableData(PdfPTable table) {
		for (Asistencia asistencia : todasLasFechas) {
			table.addCell(asistencia.getFechaAsistencia().toString());
			agregarAsistencia(table, asistencia);
			
		}
	}

	public void agregarAsistencia(PdfPTable table, Asistencia asistencia) {
		if(asistencias.contains(asistencia)){
            table.addCell("Presente");
        }else{
            table.addCell("ausente");
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
		Paragraph p = new Paragraph("Lista de Clases - " + date.toString(), font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 2.0f, 2.0f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

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