package com.online.food.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.food.modal.Restaurant;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
@Service
public class RestaurantPdfService {
    public ByteArrayInputStream createPdf(List<Restaurant> restaurants) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            Paragraph title = new Paragraph("Restaurants Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add city details table
            PdfPTable table = new PdfPTable(7); // Number of columns in the table
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add city details to the table
            for (Restaurant restaurant : restaurants) {
                addRestaurantToTable(table, restaurant);
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
        Stream.of("Restaurant_ID", "Restaurant_Name", "Restaurant_PhoneNumber","Restaurant_Manager_Name","Restaurant_Address","Restaurant_City","Restaurant_Area")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRestaurantToTable(PdfPTable table, Restaurant restaurant) {
        table.addCell(String.valueOf(restaurant.getRestaurantId()));
        table.addCell(restaurant.getRestaurantName());
        table.addCell(String.valueOf(restaurant.getRestaurantPhoneNumber()));
        table.addCell(restaurant.getCustomer().getCustomerName());
        table.addCell(restaurant.getRestaurantAddress());
        table.addCell(restaurant.getCity().getCityName());
        table.addCell(restaurant.getArea().getAreaName());
    }
}
