package com.online.food.services.excel;

import com.online.food.modal.Product;
import com.online.food.modal.Restaurant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class ProductExcelService {

    Logger logger = LoggerFactory.getLogger(ProductExcelService.class);
    public static String[] HEADERS = {
            "Product_ID"
            ,"Product_Name",
            "Category_Name",
            "SubCategory_Name",
            "Restaurant_Name","Product_Price" ,
            "Product_Description"
    };

    public static String SHEET_NAME = "Products_Details";

    public Workbook dataToExcel(List<Product> list) throws IOException {
        //create work
        Workbook workbook = new XSSFWorkbook();


        try {

            //create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            //create Row : Header Raw
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            //Value raw

            int rowIndex = 1;
            for (Product product : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue("#PRODUCT-" + product.getProductId());
                dataRow.createCell(1).setCellValue(product.getProductName());
                dataRow.createCell(2).setCellValue(product.getSubCategory().getCategory().getCategoryName());
                dataRow.createCell(3).setCellValue(product.getSubCategory().getSubCategoryName());
                dataRow.createCell(4).setCellValue(product.getRestaurant().getRestaurantName());
                dataRow.createCell(5).setCellValue(product.getProductPrice());
                dataRow.createCell(6).setCellValue(product.getProductDiscription());
            }


            logger.info("Downloaded");
            return workbook;

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fail to input data to excel");
            return null;
        }

    }
}
