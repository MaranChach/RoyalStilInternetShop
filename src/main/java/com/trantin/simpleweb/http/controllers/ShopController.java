package com.trantin.simpleweb.http.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import com.trantin.simpleweb.http.model.PaymentModel;
import com.trantin.simpleweb.http.utils.CookieUtil;
import com.trantin.simpleweb.http.utils.EmailThread;
import com.trantin.simpleweb.http.utils.PaymentTest;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.util.List;
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

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private MetadataDao metadataDao;

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping("/")
    public String getMainPage(Model model){
        System.out.println(userIdCookie.getValue());

        model.addAttribute("images", imageDao.getAll());

        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("newProducts", productDao.getLastThree());

        model.addAttribute("description", metadataDao.getByKey("description"));

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

    @RequestMapping("/personalPage")
    public String getPersonalPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userDao.getByUsername(authentication.getName());

        System.out.println(authentication.getName());

        Client client = user.getClient();
        List<Order> orderList = client.getOrders();

        System.out.println(orderList);

        model.addAttribute("client", client);
        model.addAttribute("orders", orderList);

        return "shop-pages/shop-personal-page";
    }

    @RequestMapping("/saveClientInfo")
    public String saveClientInfo(@ModelAttribute Client client,
                                 @RequestParam("oldPhoneNumber") String oldPhone){

        System.out.println(client.getPhoneNumber());
        System.out.println(oldPhone);

        User user = userDao.getByUsername(client.getPhoneNumber());

        user.setClient(client);

        userDao.save(user);

        return "redirect:/personalPage";
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //authentication.setAuthenticated(false);

        System.out.println(authentication.isAuthenticated());

        if (!(authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken))){
            model.addAttribute("client", new Client());
        }
        else {
            System.out.println("Пользователь авторизован");
            Client client = userDao.getByUsername(authentication.getName()).getClient();
            model.addAttribute("client", client);
        }

        model.addAttribute("order", new Order());
        model.addAttribute("orderCartId", orderCartId);
        model.addAttribute("orderCart", orderCartDao.getById(orderCartId));
        model.addAttribute("orderSum", 0d);
        model.addAttribute("uid", new UID());

        return "shop-pages/shop-ordering-page";

    }

    @RequestMapping("/sendFullOrder")
    private String sendFullOrder(@RequestParam(value = "clientId", defaultValue = "-1") int clientId,
                                 @RequestParam("clientEmail") String clientEmail,
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
            try {
                Client client = null;

                if(clientId == 0){
                    client = new Client(clientName, clientSurname, clientPhoneNumber, clientEmail);

                    clientDao.save(client);

                    registerClient(client, "1234", "1234");
                }
                else {
                    client = clientDao.getById(clientId);
                }

                Order order = new Order();

                order.setOrderCart(orderCartDao.getById(orderCartId));
                order.setClient(client);
                order.setCurrentDate();
                order.setUid(uid);


                if (shipmentMethod.equals("ship")){
                    order.setShipmentMethod(ShipmentMethods.ship);
                    Address address = new Address(new City(city), new Street(street), houseNumber, flatNumber);
                    order.setAddress(address);
                }
                else order.setShipmentMethod(ShipmentMethods.pickup);


                EmailThread emailThread = new EmailThread(order.getClient().getEmail(), order);
                Thread thread = new Thread(emailThread);
                thread.start();

                model.addAttribute("order", order);

                if (paymentMethod.equals("cash")){
                    order.setPaymentMethod(PaymentMethods.cash);
                    //orderDao.saveOrUpdate(order);
                    orderDao.persist(order);

                    thread.start();

                    return "shop-pages/shop-order-created-page";
                }
                else {
                    order.setPaymentMethod(PaymentMethods.card);

                    //orderDao.saveOrUpdate(order);
                    orderDao.persist(order);

                    String apiResponse = PaymentTest.SendRequest(order);

                    ObjectMapper mapper = new ObjectMapper();

                    PaymentModel payment = null;

                    try {
                        payment = mapper.readValue(apiResponse, PaymentModel.class);
                    } catch (JsonProcessingException ex) {
                        ex.printStackTrace();
                    }

                    thread.start();

                    return "redirect:" + payment.getConfirmation().getConfirmation_url();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
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

    @GetMapping("/login")
    public String getLoginPage(){
        return "shop-pages/shop-login-form";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("error") Boolean error,
                               Model model){
        System.out.println("fdsafsdaaaaa");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("fdsafsd");
            return "redirect:/personalPage";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("Неудачная попытка авторизации");
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }

    @RequestMapping("/register")
    public String getRegisterPage(Model model){

        model.addAttribute("client", new Client());

        return "shop-pages/shop-register-form";
    }

    @RequestMapping("/registration")
    public String registerClient(@ModelAttribute Client client,
                                 @RequestParam("password") String password,
                                 @RequestParam("passwordConfirm") String passwordConfirm){

        if(password.equals("")){
            return "redirect:/register?passwordNotProvided";
        }

        if(!password.equals(passwordConfirm)){
            return "redirect:/register?passwordNotMatch";
        }

        clientDao.save(client);


        User user = new User();
        user.setUsername(client.getPhoneNumber());
        user.setPassword("{noop}" + password);
        user.setEnabled((byte) 1);
        user.setClient(client)  ;

        Authority authority = new Authority(user, AuthorityType.USER);

        userDao.save(user);
        authorityDao.save(authority);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/personalPage";
    }

    @RequestMapping("/categoryTest")
    public String getCategoryPage(Model model){
        model.addAttribute("products", productDao.getAll());

        return "shop-pages/shop-category-page";
    }


    @RequestMapping("/documents")
    public String getDocumentsPage(){
        return "shop-pages/shop-documents-page";
    }
}
