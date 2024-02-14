package com.online.food.services.excel;

import com.online.food.modal.Category;
import com.online.food.modal.SubCategory;
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
public class SubCategoryExcelService {

    Logger logger = LoggerFactory.getLogger(SubCategoryExcelService.class);
    public static String[] HEADERS = {
            "SubCategory_ID", "Category_Name","SubCategory_Name" ,"SubCategory_Discription"};
    public static String SHEET_NAME = "Sub_Category_Details";

    public Workbook dataToExcel(List<SubCategory> list) throws IOException {
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
            for (SubCategory subCategory : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue("#SUB_CATEGORY-" + subCategory.getSubCategoryId());
                dataRow.createCell(1).setCellValue(subCategory.getCategory().getCategoryName());
                dataRow.createCell(2).setCellValue(subCategory.getSubCategoryName());
                dataRow.createCell(3).setCellValue(subCategory.getSubCategoryDiscription());
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
