package com.online.food.services.excel;

import com.online.food.modal.Complain;
import com.online.food.modal.Offer;
import com.online.food.modal.Product;
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
public class OfferExcelService {



    Logger logger = LoggerFactory.getLogger(OfferExcelService.class);
    public static String[] HEADERS = {
            "Offer_ID"
            ,"Offer_Name",
            "Category_Name",
            "SubCategory_Name",
            "Restaurant_Name",
            "Offer_Discount" ,
            "Offer_StartDate",
            "Offer_EndDate",
            "Offer_Description"
    };

    public static String SHEET_NAME = "Offers_Details";

    public Workbook dataToExcel(List<Offer> list) throws IOException {
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
            for (Offer offer  : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue("#Offer-" + offer.getOfferId());
                dataRow.createCell(1).setCellValue(offer.getOfferName());
                dataRow.createCell(2).setCellValue(offer.getSubCategory().getCategory().getCategoryName());
                dataRow.createCell(3).setCellValue(offer.getSubCategory().getSubCategoryName());
                dataRow.createCell(4).setCellValue(offer.getRestaurant().getRestaurantName());
                dataRow.createCell(5).setCellValue(offer.getOfferDiscount());
                dataRow.createCell(6).setCellValue(offer.getStartDate());
                dataRow.createCell(6).setCellValue(offer.getEndDate());
                dataRow.createCell(6).setCellValue(offer.getOfferDescription());
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
