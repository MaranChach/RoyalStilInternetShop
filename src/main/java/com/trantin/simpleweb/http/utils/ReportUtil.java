package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.dao.OrderDao;
import com.trantin.simpleweb.http.entity.Order;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ReportUtil {

    @Autowired
    private OrderDao orderDao;

    public HSSFWorkbook getOrdersReport(LocalDate start, LocalDate end) throws Exception {

        if(start.isAfter(end))
            throw new Exception("Некорректная дата");

        OrderDao dao = new OrderDao();

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("Лист1");

        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);

        int rowNum = 0;

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Дата");
        row.createCell(1).setCellValue("Количество заказов, штук");
        row.createCell(2).setCellValue("На сумму, руб.");
        row.createCell(3).setCellValue("Количество позиций, штук");
        row.createCell(4).setCellValue("Среднее количество позиций, штук");
        row.createCell(5).setCellValue("Сумма заказов, руб.");

        for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
            row.getCell(j).setCellStyle(style);
        }


        for (int i = 0; i < ChronoUnit.DAYS.between(start, end.plusDays(1)); i++) {
            LocalDate curDate = start.plusDays(i);

            List<Order> orders = orderDao.getByDate(Date.valueOf(curDate));

            double sum = 0;
            int itemsNumber = 0;
            for (Order order : orders) {
                sum += order.orderSum();
                itemsNumber += order.getOrderCart().getItems().size();
            }

            int ordersNumber = orders.size();

            if(ordersNumber == 0)
                ordersNumber = 1;

            double avgSum = sum / ordersNumber;
            int avgItemsNumber = itemsNumber / ordersNumber;


            row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(curDate.toString());
            row.createCell(1).setCellValue(orders.size());
            row.createCell(2).setCellValue(sum);
            row.createCell(3).setCellValue(avgSum);
            row.createCell(4).setCellValue(itemsNumber);
            row.createCell(5).setCellValue(avgItemsNumber);

            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                row.getCell(j).setCellStyle(style);
            }
        }

        /*try(FileOutputStream fos = new FileOutputStream(new File("D:\\report.xls"))){
            workbook.write(fos);
        }catch (IOException e){
            e.printStackTrace();
        }*/

        return workbook;
    }

}
