package com.online.food.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.food.modal.Area;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
@Service
public class AreaPdfService {

    public ByteArrayInputStream createPdf(List<Area> areas) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            Paragraph title = new Paragraph("Area Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add city details table
            PdfPTable table = new PdfPTable(4); // Number of columns in the table
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add city details to the table
            for (Area area : areas) {
                addAreaToTable(table, area);
            }

            document.add(table);

            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Area Id", "City Name","Area Name","Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addAreaToTable(PdfPTable table, Area area) {
        table.addCell(String.valueOf(area.getAreaId()));
        table.addCell(area.getCity().getCityName());
        table.addCell(area.getAreaName());
        table.addCell(area.getAreaDiscription());
    }
}
