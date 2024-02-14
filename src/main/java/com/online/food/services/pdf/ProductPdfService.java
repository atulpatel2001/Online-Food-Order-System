package com.online.food.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.online.food.modal.Product;
import com.online.food.modal.Restaurant;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductPdfService {
    public ByteArrayInputStream createPdf(List<Product> products) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
            Paragraph title = new Paragraph("Products Details", titleFont);
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
            for (Product product : products) {
                addProductToTable(table, product);
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
        Stream.of("Product_ID", "Product_Name", "Category_Name","SubCategory_Name","Restaurant_Name","Product_Price","Product_Description")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addProductToTable(PdfPTable table, Product product) {
        table.addCell(String.valueOf(product.getProductId()));
        table.addCell(product.getProductName());
        table.addCell(product.getSubCategory().getCategory().getCategoryName());
        table.addCell(product.getSubCategory().getSubCategoryName());
        table.addCell(product.getRestaurant().getRestaurantName());
        table.addCell(String.valueOf(product.getProductPrice()));
        table.addCell(product.getProductDiscription());
    }
}
