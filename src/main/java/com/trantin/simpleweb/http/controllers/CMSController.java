package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import com.trantin.simpleweb.http.utils.Sorter;
import com.trantin.simpleweb.http.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin", produces = "text/html; charset=UTF-8")
public class CMSController {


    @Autowired
    private HttpSession session;

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
    private ImageDao imageDao;

    //endregion

    @RequestMapping(value = "/")
    public String mainView(Model model) {

        System.out.println(session.getId());

        model.addAttribute("newProduct", new Product());
        model.addAttribute("newUnit", new Unit());
        model.addAttribute("newManufacturer", new Manufacturer());
        model.addAttribute("newClient", new Client());
        model.addAttribute("newDetails", new Details());
        model.addAttribute("newOrder", new Order());
        model.addAttribute("newCategory", new Category());

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("products", productDao.getAll());
        model.addAttribute("units", unitDao.getAll());
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("manufacturers", manufacturerDao.getAll());
        model.addAttribute("orders", orderDao.getAll());
        model.addAttribute("clients", clientDao.getAll());
        model.addAttribute("details", detailsDao.getAll());

        return "admin-pages/admin-main-page";
    }

    @RequestMapping("/units")
    public String unitsView(Model model){
        model.addAttribute("units", unitDao.getAll());

        return "admin-pages/admin-units-page";
    }

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

    @RequestMapping(value = "/saveUnit")
    public String saveUnit(@ModelAttribute("unit") Unit unit){
        System.out.println(unit);

        unitDao.save(unit);

        return "redirect:/admin/units";
    }

    @RequestMapping("/deleteUnit")
    public String deleteUnit(@RequestParam("unitId") int id, Model model){
        unitDao.deleteunit(unitDao.getById(id));

        return "redirect:/admin/units";
    }

    @RequestMapping(value = "/products")
    public String productsView(Model model) {

        model.addAttribute("newProduct", new Product());
        model.addAttribute("newUnit", new Unit());
        model.addAttribute("newManufacturer", new Manufacturer());
        model.addAttribute("newClient", new Client());
        model.addAttribute("newDetails", new Details());
        model.addAttribute("newOrder", new Order());
        model.addAttribute("newCategory", new Category());

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("products", productDao.getAll());
        model.addAttribute("units", unitDao.getAll());
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("manufacturers", manufacturerDao.getAll());
        model.addAttribute("orders", orderDao.getAll());
        model.addAttribute("clients", clientDao.getAll());
        model.addAttribute("details", detailsDao.getAll());

        return "admin-pages/admin-products-view";
    }

    @RequestMapping(value = "/product")
    public String productView(Model model){
        Product product = new Product();

        model.addAttribute("product", product);

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("attribute", new DetailsAttribute());
        model.addAttribute("detailsMap", parameterDao.getMap());

        return "admin-pages/admin-product-view";
    }

    @RequestMapping("/updateProduct")
    public String productViewById(@RequestParam("productId") int id, Model model){
        model.addAttribute("product", productDao.getById(id));

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        model.addAttribute("attribute", new DetailsAttribute());
        model.addAttribute("detailsMap", parameterDao.getMap());

        return "admin-pages/admin-product-view";
    }

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



    @RequestMapping(value = "/saveProduct")
    public String saveProduct(@ModelAttribute("newProduct") Product product,
                              @RequestParam("unit") int unitId,
                              @RequestParam("category") int categoryId,
                              @RequestParam("manufacturer") int manufacturerId) {

        product.setUnit(unitDao.getById(unitId));
        product.setCategory(categoryDao.getById(categoryId));
        product.setManufacturer(manufacturerDao.getById(manufacturerId));

        product.setImageUrl(Validator.trimImageUrl(product.getImageUrl()));

        System.out.println(product);

        productDao.save(product);

        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/deleteProduct")
    public String deleteProduct(@RequestParam("productId") int id) {

        productDao.delete(productDao.getById(id));
        System.out.println(id);
        return "1";//"redirect:/admin/products";
    }



    @RequestMapping("/product/{id}")
    public String productViewById(Model model, @PathVariable int id){
        model.addAttribute("product", productDao.getById(id));

        model.addAttribute("unitsMap", unitDao.getMap());
        model.addAttribute("categoriesMap", categoryDao.getMap());
        model.addAttribute("manufacturersMap", manufacturerDao.getMap());

        return "admin-pages/admin-product-view";
    }

    @RequestMapping("/category")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());

        return "admin-pages/admin-category-view";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(Model model, @RequestParam("id") int id){
        model.addAttribute("category", categoryDao.getById(id));

        return "admin-pages/admin-category-view";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(Model model, @RequestParam("categoryId") int id){
        categoryDao.delete(categoryDao.getById(id));
        System.out.println(id);
        System.out.println("fdsafdsaf");
        return "1";//"admin-pages/admin-category-view";
    }


    @RequestMapping("/orders")
    private String ordersView(Model model){
        List<Order> orders = orderDao.getAllSortedByDate();

        model.addAttribute("orders", orders);
        model.addAttribute("ordersConf", Sorter.getSortedByConfirmOrders(orders, true));
        model.addAttribute("ordersNotConf", Sorter.getSortedByConfirmOrders(orders, false));

        model.addAttribute("orderSum", 0d);

        return "admin-pages/admin-orders-view";
    }

    @RequestMapping("/order")
    private String orderView(@RequestParam("orderId") int id, Model model){
        model.addAttribute("order", orderDao.getById(id));
        model.addAttribute("iterator", 0);
        model.addAttribute("orderSum", 0d);

        return "admin-pages/admin-order-view";
    }

    @RequestMapping("/deleteOrder")
    private String deleteOrder(@RequestParam("orderId") int id){
        orderDao.delete(orderDao.getById(id));

        return "redirect:/admin/orders";
    }

    @RequestMapping("/details")
    private String detailsView(Model model){
        model.addAttribute("newParameter", new DetailsParameter());

        model.addAttribute("detailsParameters", parameterDao.getAll());

        model.addAttribute("units", unitDao.getMap());


        return "admin-pages/admin-details-view";
    }

    @RequestMapping("/saveDetailsParameter")
    private String saveDetailsParameter(@ModelAttribute("newParameter") DetailsParameter detailsParameter,
                                        @RequestParam("unit") int unitId,
                                        Model model){
        detailsParameter.setUnit(unitDao.getById(unitId));

        parameterDao.save(detailsParameter);

        System.out.println(detailsParameter);

        return "redirect:/admin/details";
    }

    @RequestMapping("/redactor")
    public String redactorView(Model model){
        model.addAttribute("newImage", new Image());
        model.addAttribute("images", imageDao.getAll());

        return "admin-pages/admin-shop-redactor";
    }

    @RequestMapping("/saveImage")
    private String saveMainPageImage(@ModelAttribute("newImage") Image image){
        image.setUrl(Validator.trimImageUrl(image.getUrl()));

        imageDao.save(image);

        return "redirect:/admin/redactor";
    }

    @RequestMapping("/deleteImage")
    private String saveMainPageImage(@RequestParam("imageId") int imageId){
        imageDao.delete(imageDao.getById(imageId));

        return "redirect:/admin/redactor";
    }

    @RequestMapping(value = "/saveManufacturer")
    public String saveManufacturer(@ModelAttribute("newManufacturer") Manufacturer manufacturer){
        System.out.println(manufacturer);

        manufacturerDao.save(manufacturer);

        return "redirect:/main/admin";
    }

    @RequestMapping(value = "/saveCategory")
    public String saveCategory(@ModelAttribute("newCategory") Category category){
        System.out.println(category);

        category.setImageUrl(Validator.trimImageUrl(category.getImageUrl()));

        categoryDao.save(category);

        return "redirect:/admin/products";
    }
}
