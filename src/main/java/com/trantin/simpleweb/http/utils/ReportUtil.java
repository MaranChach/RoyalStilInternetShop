package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.dao.OrderDao;
import com.trantin.simpleweb.http.dao.ProductDao;
import com.trantin.simpleweb.http.dao.TableColumnsDao;
import com.trantin.simpleweb.http.entity.Order;
import com.trantin.simpleweb.http.entity.Product;
import com.trantin.simpleweb.http.entity.TableColumn;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.font.encoding.Encoding;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import sun.font.TrueTypeFont;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ReportUtil {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private TableColumnsDao columnsDao;

    @Autowired
    private ResourceLoader resourceLoader;


    public HSSFWorkbook getOrdersReportXls(LocalDate start, LocalDate end) throws Exception {

        if(start.isAfter(end))
            throw new RuntimeException("Некорректная дата");


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
        row.createCell(3).setCellValue("Средняя сумма заказов, руб.");
        row.createCell(4).setCellValue("Количество позиций, штук");
        row.createCell(5).setCellValue("Среднее количество позиций, штук");

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

    public HSSFWorkbook getProductsTableXls(){
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

    public PDDocument getOrdersReportPdf(LocalDate start, LocalDate end) throws IOException{
        Resource resource = resourceLoader.getResource("classpath:times.ttf");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Инициализация содержимого страницы
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Установка шрифта и размера шрифта для заголовка
        contentStream.setFont(PDType0Font.load(document, resource.getFile()), 12);

        // Заголовок таблицы
        String[] tableHeader = {
                "Дата",
                "Количество заказов, штук",
                "На сумму, руб.",
                "Средняя сумма заказов, руб.",
                "Количество позиций, штук",
                "Среднее количество позиций, штук"
        };

        // Ширина ячейки таблицы
        float cellWidth = 100f;

        // Высота ячейки таблицы
        float cellHeight = 20f;

        // Координаты начала таблицы
        float startX = 50f;
        float startY = 700f;

        // Рисуем заголовок таблицы
        drawTableHeader(contentStream, document, tableHeader, cellWidth, cellHeight, startX, startY, resource);

        float currentY = startY - cellHeight; // Начальная координата Y для строк таблицы

        // Добавляем строки в таблицу
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

            String[] rowData = {
                    curDate.toString(),
                    String.valueOf(orders.size()),
                    String.valueOf(sum),
                    String.valueOf(avgSum),
                    String.valueOf(itemsNumber),
                    String.valueOf(avgItemsNumber)
            };

            drawTableRow(contentStream, document, rowData, cellWidth, cellHeight, startX, currentY, resource);
            currentY -= cellHeight;
        }

        // Закрываем содержимое страницы
        contentStream.close();

        return document;
    }

    private static void drawTableHeader(PDPageContentStream contentStream, PDDocument document, String[] tableHeader,
                                        float cellWidth, float cellHeight, float startX, float startY, Resource font) throws IOException {
        float currentX = startX;
        float currentY = startY;

        for (String header : tableHeader) {
            // Рисуем прямоугольник ячейки
            contentStream.setLineWidth(1f);
            contentStream.setNonStrokingColor(220, 220, 220); // Серый цвет для фона ячейки
            contentStream.addRect(currentX, currentY, cellWidth, cellHeight);
            contentStream.fill();

            // Нарисовать текст заголовка
            contentStream.setFont(PDType0Font.load(document, font.getFile()), 10);
            contentStream.setNonStrokingColor(0, 0, 0); // Черный цвет для текста
            contentStream.beginText();
            contentStream.newLineAtOffset(currentX + 2, currentY + 5); // Смещение текста внутри ячейки
            contentStream.showText(header);
            contentStream.endText();

            currentX += cellWidth;
        }
    }

    private static void drawTableRow(PDPageContentStream contentStream, PDDocument document, String[] rowData,
                                     float cellWidth, float cellHeight, float startX, float startY, Resource font) throws IOException {
        float currentX = startX;
        float currentY = startY;

        for (String cellData : rowData) {
            // Рисуем прямоугольник ячейки
            contentStream.setLineWidth(1f);
            contentStream.setNonStrokingColor(255, 255, 255); // Белый цвет для фона ячейки
            contentStream.addRect(currentX, currentY, cellWidth, cellHeight);
            contentStream.fill();

            // Рисуем текст ячейки
            contentStream.setFont(PDType0Font.load(document, font.getFile()), 10);
            contentStream.setNonStrokingColor(0, 0, 0); // Черный цвет для текста
            contentStream.beginText();
            contentStream.newLineAtOffset(currentX + 2, currentY + 5); // Смещение текста внутри ячейки
            contentStream.showText(cellData);
            contentStream.endText();

            currentX += cellWidth;
        }
    }

    public HSSFWorkbook getTables(){

        String[] tables = {"addresses", "authorities", "categories", "cities", "clients",
        "details", "details_attribute", "details_parameters", "main_page_images",
        "manufacturers", "metadata", "order_cart", "order_cart_item", "orders",
        "products", "streets", "units", "users"};

/*
        List<TableColumn> addressesColumns = columnsDao.getByTable("addresses");
        List<TableColumn> authoritiesColumns = columnsDao.getByTable("authorities");
        List<TableColumn> categoriesColumns = columnsDao.getByTable("categories");
        List<TableColumn> citiesColumns = columnsDao.getByTable("cities");
        List<TableColumn> clientsColumns = columnsDao.getByTable("clients");
        List<TableColumn> addressesColumns = columnsDao.getByTable("addresses");
        List<TableColumn> addressesColumns = columnsDao.getByTable("addresses");
        List<TableColumn> addressesColumns = columnsDao.getByTable("addresses");
*/

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

        HSSFFont font = workbook.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((byte) 12);

        style.setFont(font);

        int rowNum = 0;

        Row row = null;

        for (int i = 0; i < tables.length; i++) {
            List<TableColumn> columns = columnsDao.getByTable(tables[i]);

            row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue(tables[i]);
            /*for (int y = 0; y < 5; y++) {
                row.getCell(y).setCellStyle(style);
            }*/
            sheet.addMergedRegion(new CellRangeAddress(
                    rowNum,
                    rowNum,
                    0,
                    4
            ));
            row.getCell(0).setCellStyle(style);


            row = sheet.createRow(++rowNum);
            row.createCell(0).setCellValue("KEY");
            row.createCell(1).setCellValue("NAME");
            row.createCell(2).setCellValue("TYPE");
            row.createCell(3).setCellValue("REQUIRED?");
            row.createCell(4).setCellValue("NOTES");

            for (int y = 0; y < 5; y++) {
                row.getCell(y).setCellStyle(style);
            }

            for (int j = 0; j < columns.size(); j++) {
                TableColumn column = columns.get(j);

                row = sheet.createRow(++rowNum);
                row.createCell(0).setCellValue("");
                row.createCell(1).setCellValue(column.getName());
                row.createCell(2).setCellValue(column.getData_type());
                row.createCell(3).setCellValue(column.getIs_required());
                row.createCell(4).setCellValue("");

                for (int k = 0; k < 5; k++) {
                    row.getCell(k).setCellStyle(style);
                }
            }
            row = sheet.createRow(++rowNum);
        }
        return workbook;
    }

}
