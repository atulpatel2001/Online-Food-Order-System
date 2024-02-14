package com.online.food.services.excel;

import com.online.food.modal.Restaurant;
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
public class RestaurantExcelService {
    Logger logger = LoggerFactory.getLogger(RestaurantExcelService.class);
    public static String[] HEADERS = {
            "Restaurant_ID",
            "Restaurant_Name","Restaurant_PhoneNumber" ,
            "Restaurant_Manager_Name","Restaurant_Address",
            "Restaurant_City","Restaurant_Area"
    };

    public static String SHEET_NAME = "Restaurants_Details";

    public Workbook dataToExcel(List<Restaurant> list) throws IOException {
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
            for (Restaurant restaurant : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue("#RESTAURANT-" + restaurant.getRestaurantId());
                dataRow.createCell(1).setCellValue(restaurant.getRestaurantName());
                dataRow.createCell(2).setCellValue(restaurant.getRestaurantPhoneNumber());
                dataRow.createCell(3).setCellValue(restaurant.getCustomer().getCustomerName());
                dataRow.createCell(4).setCellValue(restaurant.getRestaurantAddress());
                dataRow.createCell(5).setCellValue(restaurant.getCity().getCityName());
                dataRow.createCell(6).setCellValue(restaurant.getArea().getAreaName());
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
