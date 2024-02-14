package com.online.food.services.excel;

import com.online.food.modal.Complain;
import com.online.food.modal.Offer;
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
public class ComplainExcelService {
    Logger logger = LoggerFactory.getLogger(ComplainExcelService.class);
    public static String[] HEADERS = {
            "Complain_ID"
            ,"Complain_Subject",
            "Restaurant_Name",
            "Complain_Date" ,
            "Complain_Reply",
            "Complain_ReplyDate",
            "Complain_Status",
            "Complain_Description"
    };

    public static String SHEET_NAME = "Complain_Details";

    public Workbook dataToExcel(List<Complain> list) throws IOException {
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
            for (Complain complain  : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue("#Complain-" + complain.getComplainId());
                dataRow.createCell(1).setCellValue(complain.getComplainSubject());
                dataRow.createCell(2).setCellValue(complain.getRestaurant().getRestaurantName());
                dataRow.createCell(3).setCellValue(complain.getComplainDate());
                dataRow.createCell(4).setCellValue(complain.getReply());
                dataRow.createCell(5).setCellValue(complain.getReplyDate());
                dataRow.createCell(6).setCellValue(complain.getComplainStatus());
                dataRow.createCell(7).setCellValue(complain.getComplainDescription());

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
