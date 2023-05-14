package com.trantin.simpleweb.http.controllers;


import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import com.trantin.simpleweb.http.utils.CookieUtil;
import com.trantin.simpleweb.http.utils.EmailThread;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
import java.util.Optional;

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
    private OrderDao orderDao;

    @Autowired
    private OrderCartDao orderCartDao;

    @Autowired
    private OrderCartItemDao orderCartItemDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ImageDao imageDao;


    @RequestMapping("/")
    public String getMainPage(Model model){
        System.out.println(userIdCookie.getValue());

        model.addAttribute("images", imageDao.getAll());

        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("newProducts", productDao.getLastThree());

        return "shop-pages/shop-main-page";
    }

    @RequestMapping("/catalog")
    public String getCatalogPage(Model model){
        model.addAttribute("categories", categoryDao.getAll());

        return "shop-pages/shop-catalog-page";
    }

    @RequestMapping("/category")
    public String getCategoryPage(@RequestParam int categoryId , Model model){
        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("curCategory", categoryDao.getById(categoryId).getName());

        model.addAttribute("products",
                productDao.getByCategoryId(categoryId));

        return "shop-pages/shop-category-page";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("searchText") String searchText,
                         Model model){

        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("curCategory", "Результаты поиска по запросу \"" + searchText + "\"");

        model.addAttribute("products", productDao.searchByName(searchText));

        return "shop-pages/shop-category-page";
    }

    @RequestMapping("/product")
    public String getProductPage(@RequestParam int productId,
                                 Model model){
        model.addAttribute("product", productDao.getById(productId));

        return "shop-pages/shop-product-page";
    }

    @RequestMapping("/personal-page")
    public String getPersonalPage(Model model){
        return "shop-pages/shop-personal-page";
    }


    @RequestMapping("/saveToCart")
    public String saveProductToCart(@RequestParam("productId") int productId,
                                    @RequestParam(name = "productNumber", defaultValue = "1") int productNumber,
                                    @CookieValue(required = false, name = "USERID") String sessionId,
                                    HttpSession session,
                                    HttpServletResponse response){
        System.out.println(productNumber);

        if (sessionId == null){
            response.addCookie(CookieUtil.getRandomUserIdCookie());
        }

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

        OrderCartItem newItem = null;

        try{
            newItem = orderCartItemDao.getItemFromCart(orderCart.getId(), productId);
            newItem.setNumber(newItem.getNumber() + productNumber);
            orderCartItemDao.saveOrderCart(newItem);
            return "redirect:/cart";
        }
        catch (Exception e){
            System.out.println("Creating new item");
            newItem = new OrderCartItem(productDao.getById(productId), productNumber);
        }
        orderCart.addItemInCart(newItem);

        orderCartDao.saveOrderCart(orderCart);

        return "redirect:/cart";
    }

    @RequestMapping("deleteFromCart")
    private String deleteFromCart(@RequestParam("cartItemId") int cartItemId){
        orderCartItemDao.delete(orderCartItemDao.getById(cartItemId));

        return "redirect:/cart";
    }

    @RequestMapping("/cart")
    private String getCartView(Model model,
                               @CookieValue(required = false, name = "USERID") String sessionId,
                               HttpServletResponse response){
        if (sessionId == null){
            response.addCookie(CookieUtil.getRandomUserIdCookie());
        }

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

        return "shop-pages/shop-order-cart-page";
    }

    @RequestMapping("/ordering")
    private String orderingPage(@RequestParam("orderCartId") int orderCartId,
                                Model model){
        model.addAttribute("order", new Order());
        model.addAttribute("client", new Client());
        model.addAttribute("orderCartId", orderCartId);
        model.addAttribute("orderCart", orderCartDao.getById(orderCartId));
        model.addAttribute("orderSum", 0d);
        model.addAttribute("uid", new UID());

        return "shop-pages/shop-ordering-page";
    }

    @RequestMapping("/sendFullOrder")
    private String sendFullOrder(@RequestParam("clientEmail") String clientEmail,
                                 @RequestParam("clientName") String clientName,
                                 @RequestParam("clientSurname") String clientSurname,
                                 @RequestParam("clientPhoneNumber") String clientPhoneNumber,
                                 @RequestParam(value = "city", defaultValue = "") String city,
                                 @RequestParam(value = "street", defaultValue = "") String street,
                                 @RequestParam(value = "houseNumber", defaultValue = "") String houseNumber,
                                 @RequestParam(value = "flatNumber", defaultValue = "0") Integer flatNumber,
                                 @RequestParam("orderCartId") int orderCartId,
                                 @RequestParam("shipmentMethod") String shipmentMethod,
                                 @RequestParam("paymentMethod") String paymentMethod,
                                 @RequestParam("uid") String uid,
                                 HttpServletResponse response,
                                 Model model){

        if(clientEmail.equals("")
        || clientName.equals("")
        || clientSurname.equals("")
        || clientPhoneNumber.equals(""))
            throw new RuntimeException("Заполните все значения");

        try{

            response.addCookie(CookieUtil.getRandomUserIdCookie());
            Order oldOrder = orderDao.getByUid(uid);

            model.addAttribute("order", oldOrder);

            return "shop-pages/shop-order-created-page";
        }
        catch (Exception e){
            Client client = new Client(clientName, clientSurname, clientPhoneNumber, clientEmail);

            clientDao.save(client);

            Order order = new Order();

            order.setOrderCart(orderCartDao.getById(orderCartId));
            order.setClient(client);
            order.setCurrentDate();
            order.setUid(uid);


            if (paymentMethod.equals("cash"))
                order.setPaymentMethod(PaymentMethods.cash);
            else order.setPaymentMethod(PaymentMethods.card);

            if (shipmentMethod.equals("ship")){
                order.setShipmentMethod(ShipmentMethods.ship);
                Address address = new Address(new City(city), new Street(street), houseNumber, flatNumber);
                order.setAddress(address);
            }
            else order.setShipmentMethod(ShipmentMethods.pickup);

            orderDao.persist(order);

            EmailThread emailThread = new EmailThread(order.getClient().getEmail(), order);
            Thread thread = new Thread(emailThread);
            thread.start();

            model.addAttribute("order", order);

            return "shop-pages/shop-order-created-page";
        }
    }

    @RequestMapping("/sendOrder")
    private String saveOrder(@RequestParam("orderCartId") int id,
                             @RequestParam("name") String name,
                             @RequestParam("phoneNumber") String phoneNumber,
                             @RequestParam(value = "comment", defaultValue = "") String comment,
                             Model model){
        Order order = new Order();
        Client client = new Client();
        client.setName(name);
        client.setPhoneNumber(phoneNumber);

        order.setOrderCart(orderCartDao.getById(id));
        order.setClient(client);
        order.setCurrentDate();

        model.addAttribute("order", order);

        clientDao.save(client);
        orderDao.persist(order);

        return "shop-pages/shop-order-created-page";
    }


    @RequestMapping("/categoryTest")
    public String getCategoryPage(Model model){
        model.addAttribute("products", productDao.getAll());

        return "shop-pages/shop-category-page";
    }
    @RequestMapping("/productTest")
    public String getTestProductPage(){
        return "shop-pages/shop-product-page";
    }
}
