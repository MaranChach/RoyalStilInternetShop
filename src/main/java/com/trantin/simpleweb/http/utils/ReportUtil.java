package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.dao.OrderDao;
import com.trantin.simpleweb.http.dao.ProductDao;
import com.trantin.simpleweb.http.entity.Order;
import com.trantin.simpleweb.http.entity.Product;
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

    @Autowired
    private ProductDao productDao;

    public HSSFWorkbook getOrdersReport(LocalDate start, LocalDate end) throws Exception {

        if(start.isAfter(end))
            throw new Exception("Некорректная дата");


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

        row.createCell(0).setCellValue("Отчёт по продажам за " + start + " - " + end);


        row = sheet.createRow(++rowNum);
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

    public HSSFWorkbook getProductsTable(){
        List<Product> products = productDao.getAll();

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
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Артикул");
        row.createCell(2).setCellValue("Наименование");
        row.createCell(3).setCellValue("Количество");
        row.createCell(4).setCellValue("Единица измерения");
        row.createCell(5).setCellValue("Категория");
        row.createCell(6).setCellValue("Производитель");
        row.createCell(7).setCellValue("Цена");
        row.createCell(8).setCellValue("Картинка");
        row.createCell(9).setCellValue("Описание");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getArticle());
            row.createCell(2).setCellValue(product.getName());
            row.createCell(3).setCellValue(product.getNumber());
            row.createCell(4).setCellValue(product.getUnit().getName());
            row.createCell(5).setCellValue(product.getCategory().getName());
            row.createCell(6).setCellValue(product.getManufacturer().getName());
            row.createCell(7).setCellValue(product.getCost());
            row.createCell(8).setCellValue(product.getImageUrl());
            row.createCell(9).setCellValue(product.getDescription());
        }

        return workbook;
    }
}
