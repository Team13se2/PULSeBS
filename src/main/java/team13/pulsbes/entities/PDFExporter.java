package team13.pulsbes.entities;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import team13.pulsbes.dtos.StudentDTO;


public class PDFExporter {
	private List<StudentDTO> listStudents;
	
	public PDFExporter(List<StudentDTO> listStudents) {
		this.listStudents = listStudents;
	}

	Logger log = Logger.getLogger("PDFExporter");

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Student ID", font));		
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Surname", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Birthday", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);			
			
	}
	
	private void writeTableData(PdfPTable table) {
		for (StudentDTO student : listStudents) {
			table.addCell(student.getId());
			table.addCell(student.getName());
			table.addCell(student.getSurname());
            table.addCell(student.getBirthday());
            table.addCell(student.getSSN());
            table.addCell(student.getEmail());
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		try (Document document = new Document(PageSize.A4);) {
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p = new Paragraph("Contact Report", font);
		p.setAlignment(Element.ALIGN_CENTER);
		
		document.add(p);
		
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 1.5f, 1.5f, 1.5f, 2.0f, 3.5f});
		table.setSpacingBefore(10);
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);
		
		document.close();

		}
		catch (Exception e) {
			log.throwing(this.getClass().getName(), "export", e);
		}
		
	}
}