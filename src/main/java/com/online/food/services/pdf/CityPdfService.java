package com.online.food.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.online.food.modal.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CityPdfService {
    public ByteArrayInputStream createPdf(List<City> cities) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            Paragraph title = new Paragraph("City Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add city details table
            PdfPTable table = new PdfPTable(3); // Number of columns in the table
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add city details to the table
            for (City city : cities) {
                addCityToTable(table, city);
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
        Stream.of("City ID", "City Name", "Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addCityToTable(PdfPTable table, City city) {
        table.addCell(String.valueOf(city.getCityId()));
        table.addCell(city.getCityName());
        table.addCell(city.getCityDiscription());
    }
}
