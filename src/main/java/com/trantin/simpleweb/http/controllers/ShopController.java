package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.Client;
import com.trantin.simpleweb.http.entity.Order;
import com.trantin.simpleweb.http.entity.OrderCart;
import com.trantin.simpleweb.http.entity.OrderCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;

@Controller
public class ShopController {

    @Autowired
    private Cookie userIdCookie;

    @Autowired
    private HttpSession session;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private OrderCartDao orderCartDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ClientDao clientDao;


    @RequestMapping("/")
    public String getMainPage(Model model){
        System.out.println(userIdCookie.getValue());

        model.addAttribute("categories", categoryDao.getAll());

        return "shop-main-page";
    }

    @RequestMapping("/catalog")
    public String getCatalogPage(Model model){
        model.addAttribute("categories", categoryDao.getAll());

        return "shop-catalog-page";
    }

    @RequestMapping("/category")
    public String getCategoryPage(@RequestParam int categoryId , Model model){
        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("curCategory", categoryDao.getById(categoryId));

        model.addAttribute("products",
                productDao.getByCategoryId(categoryId));

        return "shop-category-page";
    }

    @RequestMapping("/product")
    public String getProductPage(@RequestParam int productId, Model model){
        model.addAttribute("product", productDao.getById(productId));

        return "shop-product-page";
    }

    @RequestMapping("/personal-page")
    public String getPersonalPage(Model model){
        return "shop-personal-page";
    }


    @RequestMapping("/saveToCart")
    public void saveProductToCart(@RequestParam int productId, HttpServletResponse response){

        String sessionId = userIdCookie.getValue();

        //String sessionId = session.getId();

        System.out.println(sessionId);

        OrderCart orderCart = null;

        try{
            orderCart = orderCartDao.getBySessionId(sessionId);
        }
        catch (Exception e){
            System.out.println("Creating new cart");
        }

        if(orderCart == null){
            orderCart = new OrderCart();
            orderCart.setUid(sessionId);
        }

        orderCart.addItemInCart(new OrderCartItem(productDao.getById(productId), 1));

        //saving cookie and cart data

        response.addCookie(userIdCookie);

        orderCartDao.saveOrderCart(orderCart);
    }

    @RequestMapping("/cart")
    private String getCartView(Model model, @CookieValue("USERID") String sessionId){

        //String sessionId = session.getId();
        System.out.println(sessionId);

        model.addAttribute("client", new Client());

        OrderCart orderCart = null;

        try {
            orderCart = orderCartDao.getBySessionId(sessionId);
            model.addAttribute("orderCart", orderCart);

            Order order = new Order();
            order.setOrderCart(orderCart);

            System.out.println(order);

            model.addAttribute("order", order);
        }
        catch (Exception e){
        }

        return "shop-order-cart-page";
    }

    @RequestMapping("/sendOrder")
    private String saveOrder(@ModelAttribute("order") Order order,
                             @RequestParam("orderCartId") int id){
        System.out.println(order);

        order.setOrderCart(orderCartDao.getById(id));
        order.setCurrentDate();

        orderDao.save(order);

        return "";
    }

    @RequestMapping("saveClient")
    private String saveClient(@ModelAttribute("client") Client client){
        clientDao.save(client);

        return "";
    }



    @RequestMapping("/categoryTest")
    public String getCategoryPage(Model model){
        model.addAttribute("products", productDao.getAll());

        return "shop-category-page";
    }
    @RequestMapping("/productTest")
    public String getTestProductPage(){
        return "shop-product-page";
    }
}
