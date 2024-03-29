package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import com.trantin.simpleweb.http.exceptions.LinkException;
import com.trantin.simpleweb.http.utils.FileUtil;
import com.trantin.simpleweb.http.utils.ReportUtil;
import com.trantin.simpleweb.http.utils.Sorter;
import com.trantin.simpleweb.http.utils.Validator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/admin", produces = "text/html; charset=UTF-8")
public class CMSController {


    @Autowired
    private HttpSession session;

    @Autowired
    private ReportUtil reportUtil;

    //region DAO
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UnitDao unitDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ManufacturerDao manufacturerDao;
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private DetailsDao detailsDao;
    @Autowired
    private DetailsParameterDao parameterDao;
    @Autowired
    private DetailsAttributeDao attributeDao;
    @Autowired
    private MetadataDao metadataDao;

    @Autowired
    private ImageDao imageDao;

    //endregion


    //главная страница
    @RequestMapping(value = "/")
    public String mainView(Model model) {

        System.out.println(session.getId());

        model.addAttribute("todayOrdersNumber", orderDao.
                getByDate(Date.valueOf(LocalDate.now())).size());
        model.addAttribute("yesterdayOrdersNumber", orderDao.
                getByDate(Date.valueOf(LocalDate.now().minusDays(1))).size());
        model.addAttribute("products", productDao.getAll());
        model.addAttribute("orders", orderDao.getAll());
        model.addAttribute("newOrdersNum", orderDao.getNewOrdersNum());

        return "admin-pages/admin-main-page";
    }

    //region Units
    //страница единиц измерения
    @RequestMapping("/units")
    public String unitsView(Model model){
        model.addAttribute("units", unitDao.getAll());

        return "admin-pages/admin-units-page";
    }

    //страница единицы измерения
    @RequestMapping("/unit")
    public String unitView(Model model){
        model.addAttribute("unit", new Unit());

        return "admin-pages/admin-unit-view";
    }

    @RequestMapping("/updateUnit")
    public String unitViewById(@RequestParam("unitId") int id, Model model){
        model.addAttribute("unit", unitDao.getById(id));

        return "admin-pages/admin-unit-view";
    }

    //сохранение единицы
    @RequestMapping(value = "/saveUnit")
    public String saveUnit(@ModelAttribute("unit") Unit unit){
        if(unit.getName().equals("")){
            throw new RuntimeException("Заполните все значения");
        }

        unitDao.save(unit);

        return "redirect:/admin/units";
    }

    //удаление единицы
    @RequestMapping("/deleteUnit")
    public String deleteUnit(@RequestParam("unitId") int id, Model model) throws LinkException {
        try{
            unitDao.delete(unitDao.getById(id));
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException("Невозможно удалить, на объект сохранены ссылки");
        }

        return "redirect:/admin/units";
    }
    //endregion

    //region Manufacturers
    //страница производителей
    @RequestMapping("/manufacturers")
    public String manufacturersView(Model model){
        model.addAttribute("manufacturers", manufacturerDao.getAll());

        return "admin-pages/admin-manufacrurers-page";
    }

    //страница производителя
    @RequestMapping(value = "/manufacturer")
    public String manufacturerView(Model model){
        model.addAttribute("manufacturer", new Manufacturer());

        return "admin-pages/admin-manufacturer-view";
    }

    @RequestMapping("/updateManufacturer")
    public String manufacturerViewById(@RequestParam("manufacturerId") int id, Model model){
        model.addAttribute("manufacturer", manufacturerDao.getById(id));

        return "admin-pages/admin-manufacturer-view";
    }

    //сохранение производителя
    @RequestMapping(value = "/saveManufacturer")
    public String saveManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer){
        manufacturerDao.save(manufacturer);

        return "redirect:/admin/manufacturers";
    }
    //удаление производителя
    @RequestMapping("/deleteManufacturer")
    public String deleteManufacturer(@RequestParam("manufacturerId") int id, Model model){
        //проверка на ссылки
        try{
            manufacturerDao.delete(manufacturerDao.getById(id));
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException("Невозможно удалить, на объект сохранены ссылки");
        }

        return "redirect:/admin/manufacturers";
    }
    //endregion

    //region Products
    //список товаров
    @RequestMapping(value = "/products")
    public String productsView(Model model) {
        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("products", productDao.getAll());
        model.addAttribute("categories", categoryDao.getAll());

        return "admin-pages/admin-products-view";
    }
    //поиск товаров
    @RequestMapping("/searchProduct")
    public String search(@RequestParam("searchText") String searchText,
                         Model model){

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("products", productDao.searchByName(searchText));
        model.addAttribute("categories", categoryDao.getAll());

        return "admin-pages/admin-products-view";
    }
    //форма товара
    @RequestMapping(value = "/product")
    public String productView(Model model){
        Product product = new Product();

        model.addAttribute("product", product);

        model.addAttribute("unitsMap", unitDao.getAll());
        model.addAttribute("categoriesMap", categoryDao.getAll());
        model.addAttribute("manufacturersMap", manufacturerDao.getAll());

        model.addAttribute("attribute", new DetailsAttribute());
        model.addAttribute("detailsMap", parameterDao.getMap());

        return "admin-pages/admin-product-view";
    }
    //сохранение товара
    @RequestMapping("/updateProduct")
    public String productViewById(@RequestParam("productId") int id, Model model){
        model.addAttribute("product", productDao.getById(id));

        model.addAttribute("unitsMap", unitDao.getAll());
        model.addAttribute("categoriesMap", categoryDao.getAll());
        model.addAttribute("manufacturersMap", manufacturerDao.getAll());

        model.addAttribute("attribute", new DetailsAttribute());
        model.addAttribute("detailsMap", parameterDao.getMap());

        return "admin-pages/admin-product-view";
    }

    //сохранение товара
    @RequestMapping(value = "/saveProduct")
    public String saveProduct(@ModelAttribute("newProduct") Product product,
                              @RequestParam(value = "unit", defaultValue = "-1") int unitId,
                              @RequestParam(value = "category", defaultValue = "-1") int categoryId,
                              @RequestParam(value = "manufacturer", defaultValue = "-1") int manufacturerId,
                              Model model) {

        //проверка заполнения
        if(product.getName().equals("")){
            throw new RuntimeException("Заполните все значения");
        }
        //проверка заполнения
        if (unitId != -1)
            product.setUnit(unitDao.getById(unitId));
        else
            product.setUnit(null);
        if (categoryId != -1)
            product.setCategory(categoryDao.getById(categoryId));
        else
            product.setCategory(null);
        if (manufacturerId != -1)
            product.setManufacturer(manufacturerDao.getById(manufacturerId));
        else
            product.setManufacturer(null);

        //валидация картинки
        product.setImageUrl(Validator.trimImageUrl(product.getImageUrl()));
        productDao.save(product);

        return "redirect:/admin/products";
    }

    //удаление товара
    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int id) {
        try{
            productDao.delete(productDao.getById(id));
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException("Невозможно удалить, на объект сохранены ссылки");
        }

        return "redirect:/admin/products";
    }
    //endregion

    //region Categories
    //страница категории
    @RequestMapping("/category")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());

        return "admin-pages/admin-category-view";
    }

    //сохранение категории
    @RequestMapping("/updateCategory")
    public String updateCategory(Model model, @RequestParam("id") int id){
        model.addAttribute("category", categoryDao.getById(id));

        return "admin-pages/admin-category-view";
    }
    //удаление категории
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Model model, @RequestParam("categoryId") int id){
        try{
            categoryDao.delete(categoryDao.getById(id));
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException("Невозможно удалить, на объект сохранены ссылки");
        }

        return "redirect:/admin/products";
    }

    //сохранение категории
    @RequestMapping(value = "/saveCategory")
    public String saveCategory(@ModelAttribute("newCategory") Category category){
        category.setImageUrl(Validator.trimImageUrl(category.getImageUrl()));

        categoryDao.save(category);

        return "redirect:/admin/products";
    }

    //endregion

    //region Orders
    @RequestMapping("/orders")
    private String ordersView(Model model){
        List<Order> orders = orderDao.getAll();

        model.addAttribute("orders", orders);
        model.addAttribute("ordersConf", Sorter.getSortedByConfirmOrders(orders, true));
        model.addAttribute("ordersNotConf", Sorter.getSortedByConfirmOrders(orders, false));

        model.addAttribute("orderSum", 0d);

        return "admin-pages/admin-orders-view";
    }

    @RequestMapping("/searchOrders")
    private String searchOrders(@RequestParam("searchText") String searchText,
                                Model model){
        List<Order> orders = orderDao.search(searchText);

        model.addAttribute("orders", orders);
        model.addAttribute("ordersConf", Sorter.getSortedByConfirmOrders(orders, true));
        model.addAttribute("ordersNotConf", Sorter.getSortedByConfirmOrders(orders, false));

        model.addAttribute("orderSum", 0d);

        return "admin-pages/admin-orders-view";
    }

    //страница заказа
    @RequestMapping("/order")
    private String orderView(@RequestParam("orderId") int id, Model model){
        Order order = orderDao.getById(id);

        model.addAttribute("order", order);
        model.addAttribute("iterator", 0);
        model.addAttribute("orderSum", order.orderSum());

        if (order.getShipmentMethod() == ShipmentMethods.ship){
            model.addAttribute("isShipment", true);
            model.addAttribute("orderDelivery", metadataDao.getByKey("deliveryPrice"));
        }

        if(order.isConfirmed())
            model.addAttribute("isDisabled", "disabled");
        else
            model.addAttribute("isDisabled", "");

        return "admin-pages/admin-order-view";
    }

    //удаление заказа
    @RequestMapping("/deleteOrder")
    private String deleteOrder(@RequestParam("orderId") int id){
        orderDao.delete(orderDao.getById(id));

        return "redirect:/admin/orders";
    }

    //завершение заказа
    @RequestMapping("/confirmOrder")
    private String confirmOrder(@RequestParam("orderId") int id){
        Order order = orderDao.getById(id);

        order.setConfirmed(true);

        orderDao.saveOrUpdate(order);

        return "redirect:/admin/orders";
    }
    //endregion

    //region Details
    //виды характеристик
    @RequestMapping("/details")
    private String detailsView(Model model){
        model.addAttribute("newParameter", new DetailsParameter());
        model.addAttribute("detailsParameters", parameterDao.getAll());
        model.addAttribute("units", unitDao.getMap());

        return "admin-pages/admin-details-view";
    }

    //сохранение
    @RequestMapping("/saveDetailsParameter")
    private String saveDetailsParameter(@ModelAttribute("newParameter") DetailsParameter detailsParameter,
                                        @RequestParam(value = "unit", defaultValue = "-1") int unitId,
                                        Model model){

        if(detailsParameter.getName().equals("")
        || unitId == -1){
            throw new RuntimeException("Заполните все значения");
        }

        detailsParameter.setUnit(unitDao.getById(unitId));

        parameterDao.save(detailsParameter);

        System.out.println(detailsParameter);

        return "redirect:/admin/details";
    }

    //удаление
    @RequestMapping("/deleteDetailsParameter")
    private String deleteDetailsParameter(@RequestParam("parameterId") int parameterId){
        try{
            parameterDao.delete(parameterDao.getById(parameterId));
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException("Невозможно удалить, на объект сохранены ссылки");
        }

        return "redirect:/admin/details";
    }

    //сохранение для товара
    @RequestMapping("/saveDetailsAttribute")
    private String saveDetailsAttribute(/*@ModelAttribute("attribute") DetailsAttribute attribute,*/
            @RequestParam("parameter") int parameterId,
            @RequestParam("productId") int productId,
            @RequestParam("value") double value,
            Model model){
        Product product = productDao.getById(productId);

        DetailsAttribute attribute = new DetailsAttribute();

        attribute.setParameter(parameterDao.getById(parameterId));
        attribute.setValue(value);

        if (product.getDetails() == null){
            System.out.println("У продукта нет характеристик, создание новых");

            Details details = new Details();
            detailsDao.save(details);

            System.out.println(details.getId());

            product.setDetails(details);
            productDao.save(product);
        }
        attribute.setDetails(product.getDetails());

        System.out.println("Создание аттрибута");

        attributeDao.save(attribute);

        return "redirect:/admin/updateProduct?productId=" + product.getId();
    }

    @RequestMapping("/deleteDetailsAttribute")
    private String saveDetailsAttribute(/*@ModelAttribute("attribute") DetailsAttribute attribute,*/
            @RequestParam("attributeId") int attributeId,
            @RequestParam("productId") int productId,
            Model model){

        DetailsAttribute attr = attributeDao.getById(attributeId);
        attr.setDetails(null);
        attributeDao.save(attr);
        attributeDao.delete(attr.getId());

        /*Details details = productDao.getById(productId).getDetails();
        details.getAttributes().remove(attributeDao.getById(attributeId));

        detailsDao.save(details);*/

        return "redirect:/admin/updateProduct?productId=" + productId;
    }
    //endregion

    //region Redactor
    //редактор главной страницы
    @RequestMapping("/redactor")
    public String redactorView(Model model){
        model.addAttribute("newImage", new Image());
        model.addAttribute("images", imageDao.getAll());

        Metadata desc = metadataDao.getByKey("description");
        if (desc == null){
            metadataDao.save(new Metadata("description", ""));
        }
        model.addAttribute("description", metadataDao.getByKey("description"));

        return "admin-pages/admin-shop-redactor";
    }

    //сохранение баннера
    @RequestMapping("/saveImage")
    private String saveMainPageImage(@ModelAttribute("newImage") Image image){
        image.setUrl(Validator.trimImageUrl(image.getUrl()));

        imageDao.save(image);

        return "redirect:/admin/redactor";
    }

    //удаление баннера
    @RequestMapping("/deleteImage")
    private String saveMainPageImage(@RequestParam("imageId") int imageId){
        imageDao.delete(imageDao.getById(imageId));

        return "redirect:/admin/redactor";
    }

    //сохранение описания
    @RequestMapping(value = "/updateDescription")
    private String updateDescription(){

        FileUtil fileUtil = new FileUtil();

        try {
            fileUtil.setShopDesc("fdsafdsaofhdsahidagdg");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/admin/redactor";
    }
    //сохранение описания
    @RequestMapping("/saveDescription")
    private String saveDescription(@ModelAttribute("description") Metadata metaDesc){
        metadataDao.save(metaDesc);

        return "redirect:/admin/redactor";
    }

    //endregion

    //region Reports
    //отчёты
    @RequestMapping("/reports")
    public String reportsPage(){
        return "admin-pages/admin-reports-page";
    }

    //отчёт о заказах
    @RequestMapping(value = "/ordersReport")
    private void getOrdersReportXls(HttpServletResponse response,
                                 @RequestParam(value = "start") String start,
                                 @RequestParam("end") String end){

        //проверка заполнения
        if (start.equals("") || end.equals("")){
            throw new RuntimeException("Выберите период");
        }

        LocalDate dateStart = LocalDate.parse(start);
        LocalDate dateEnd = LocalDate.parse(end);

        HSSFWorkbook workbook = null;
        try {
            workbook = reportUtil.getOrdersReportXls(dateStart, dateEnd);
        } catch (Exception e) {
            throw new RuntimeException("Некорректная дата");
        }

        try {
            response.setHeader("Content-disposition", "attachment;filename=report " + dateStart + " - " + dateEnd + ".xls");
            response.setContentType("application/vnd.ms-excel");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        //return "redirect:/admin/reports";
    }

    @RequestMapping(value = "/ordersReportPdf")
    private void getOrdersReportPdf(HttpServletResponse response,
                                    @RequestParam(value = "start") String start,
                                    @RequestParam("end") String end){

        if (start.equals("") || end.equals("")){
            throw new RuntimeException("Выберите период");
        }

        LocalDate dateStart = LocalDate.parse(start);
        LocalDate dateEnd = LocalDate.parse(end);

        PDDocument workbook = null;
        try {
            workbook = reportUtil.getOrdersReportPdf(dateStart, dateEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.setHeader("Content-disposition", "inline;filename=report " + dateStart + " - " + dateEnd + ".pdf");
            response.setContentType(MediaType.APPLICATION_PDF.toString());

            workbook.save(response.getOutputStream());
            workbook.close();
            response.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        //return "redirect:/admin/reports";
    }

    @RequestMapping(value = "/productsReport")
    private void getOrdersReport(HttpServletResponse response){

        HSSFWorkbook workbook = null;
        try {
            workbook = reportUtil.getProductsTableXls();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.setHeader("Content-disposition", "attachment;filename=products.xls");
            response.setContentType("application/vnd.ms-excel");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        // return "redirect:/admin/reports";
    }

    @RequestMapping(value = "/tables")
    private void getTablesData(HttpServletResponse response){

        HSSFWorkbook workbook = null;
        try {
            workbook = reportUtil.getTables();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.setHeader("Content-disposition", "attachment;filename=tables.xls");
            response.setContentType("application/vnd.ms-excel");

            workbook.write(response.getOutputStream());
            response.getOutputStream().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
