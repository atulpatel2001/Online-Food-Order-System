package com.online.food.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.food.modal.Offer;
import com.online.food.modal.Restaurant;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class OfferPdfService {
    public ByteArrayInputStream createPdf(List<Offer> offers) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            Paragraph title = new Paragraph("Offers Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add city details table
            PdfPTable table = new PdfPTable(9); // Number of columns in the table
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add city details to the table
            for (Offer offer : offers) {
                addOfferToTable(table, offer);
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
        Stream.of("Offer_ID", "Offer_Name", "Category_Name","SubCategory_Name","Restaurant_Name","Offer_Discount","Offer_StartDate","Offer_EndDate","Offer_Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addOfferToTable(PdfPTable table,Offer offer) {
        table.addCell(String.valueOf(offer.getOfferId()));
        table.addCell(offer.getOfferName());
        table.addCell(offer.getSubCategory().getCategory().getCategoryName());
        table.addCell(offer.getSubCategory().getSubCategoryName());
        table.addCell(offer.getRestaurant().getRestaurantName());
        table.addCell(String.valueOf(offer.getOfferDiscount()));
        table.addCell(String.valueOf(offer.getStartDate()));
        table.addCell(String.valueOf(offer.getEndDate()));
        table.addCell(offer.getOfferDescription());
    }
}
