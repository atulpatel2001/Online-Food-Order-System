package com.online.food.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.food.modal.Complain;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ComplainPdfService {
    public ByteArrayInputStream createPdf(List<Complain> complains) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            Paragraph title = new Paragraph("Complains Details", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add city details table
            PdfPTable table = new PdfPTable(8); // Number of columns in the table
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add city details to the table
            for (Complain complain : complains) {
                addComplainToTable(table, complain);
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
        Stream.of("Complain_ID", "Complain_Subject", "Restaurant_Name","Complain_Date","Complain_Reply","Complain_ReplyDate","Complain_Status","Complain_Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addComplainToTable(PdfPTable table,Complain complain) {
        table.addCell(String.valueOf(complain.getComplainId()));
        table.addCell(complain.getComplainSubject());
        table.addCell(complain.getRestaurant().getRestaurantName());
        table.addCell(String.valueOf(complain.getComplainDate()));
        table.addCell(complain.getReply());
        table.addCell(String.valueOf(complain.getReplyDate()));
        table.addCell(complain.getComplainStatus());
        table.addCell(complain.getComplainDescription());
    }
}
