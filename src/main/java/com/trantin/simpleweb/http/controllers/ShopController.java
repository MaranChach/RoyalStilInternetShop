package com.trantin.simpleweb.http.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import com.trantin.simpleweb.http.model.PaymentModel;
import com.trantin.simpleweb.http.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.util.List;

@Controller
public class ShopController {



    //region DAO
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
    private ManufacturerDao manufacturerDao;
    //endregion

    @Autowired
    private AuthenticationManager authenticationManager;


    //Главная страница
    @RequestMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("images", imageDao.getAll());

        model.addAttribute("categories", categoryDao.getAll());

        model.addAttribute("newProducts", productDao.getLastThree());

        model.addAttribute("description", metadataDao.getByKey("description"));

        return "shop-pages/shop-main-page";
    }


    //Список категорий
    @RequestMapping("/catalog")
    public String getCatalogPage(Model model){
        model.addAttribute("categories", categoryDao.getAll());

        return "shop-pages/shop-catalog-page";
    }

    //Товары из категории
    @RequestMapping("/category")
    public String getCategoryPage(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                                  @RequestParam(value = "searchText", defaultValue = "") String searchText,
                                  @RequestParam(value = "sortType", defaultValue = "none") String sortType,
                                  @RequestParam(value = "manufacturerId", defaultValue = "-1") int manufacturerId,
                                  Model model){

        /*if(categoryId != 0 && searchText.isEmpty()){
            model.addAttribute("curCategory", categoryDao.getById(categoryId));
            //Проверка выбранной сортировки
            switch (sortType){
                case "none" : {
                    model.addAttribute("products",
                            productDao.getByCategoryId(categoryId));
                    break;
                }
                case "costAsc" : {
                    model.addAttribute("products",
                            productDao.getSortedByCost(categoryId, true));
                    break;
                }
                case "costDesc" : {
                    model.addAttribute("products",
                            productDao.getSortedByCost(categoryId, false));
                    break;
                }
            }
        }
        else {
            //Проверка выбранной сортировки
            model.addAttribute("curCategory", new Category("Результаты поиска по запросу \"" + searchText + "\""));
            switch (sortType){
                case "none" : {
                    model.addAttribute("products",
                            productDao.search(searchText));
                    break;
                }
                case "costAsc" : {
                    model.addAttribute("products",
                            productDao.searchSortedByCost(searchText, true));
                    break;
                }
                case "costDesc" : {
                    model.addAttribute("products",
                            productDao.searchSortedByCost(searchText, false));
                    break;
                }
            }
        }*/

        if (!searchText.isEmpty()){
            model.addAttribute("curCategory", new Category("Результаты поиска по запросу \"" + searchText + "\""));
        }
        if (categoryId != 0){
            model.addAttribute("curCategory", categoryDao.getById(categoryId));
        }
        if (manufacturerId != -1){
            model.addAttribute("curManufacturer", manufacturerDao.getById(manufacturerId));
        }

        model.addAttribute("products", productDao.getByFilters(searchText, categoryId, manufacturerId, sortType));

        model.addAttribute("searchText", searchText);
        model.addAttribute("sortType", sortType);
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("manufacturers", manufacturerDao.getAll());

        return "shop-pages/shop-category-page";
    }

    //Список товаров по результатам поиска
    @RequestMapping("/search")
    public String search(@RequestParam("searchText") String searchText,
                         @RequestParam(value = "sortType", defaultValue = "none") String sortType,
                         Model model){

        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("curCategory", new Category("Результаты поиска по запросу \"" + searchText + "\""));

        switch (sortType){
            case "none" : {
                model.addAttribute("products",
                        productDao.search(searchText));
                break;
            }
            case "costAsc" : {
                model.addAttribute("products",
                        productDao.searchSortedByCost(searchText, true));
                break;
            }
            case "costDesc" : {
                model.addAttribute("products",
                        productDao.searchSortedByCost(searchText, false));
                break;
            }
        }

        return "shop-pages/shop-category-page";
    }

    //Страница товара
    @RequestMapping("/product")
    public String getProductPage(@RequestParam("productId") int productId,
                                 Model model){
        model.addAttribute("product", productDao.getById(productId));

        return "shop-pages/shop-product-page";
    }

    //Личный кабинет пользователя
    @RequestMapping("/personalPage")
    public String getPersonalPage(Model model){
        //Получение аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.getByUsername(authentication.getName());

        Client client = user.getClient();
        List<Order> orderList = client.getOrders();

        model.addAttribute("client", client);
        model.addAttribute("orders", orderList);

        return "shop-pages/shop-personal-page";
    }


    //Сохранение личных данных клиента
    @RequestMapping("/saveClientInfo")
    public String saveClientInfo(@ModelAttribute Client client,
                                 @RequestParam("oldPhoneNumber") String oldPhone){

        User user = userDao.getByUsername(client.getPhoneNumber());
        user.setClient(client);
        userDao.save(user);

        return "redirect:/personalPage";
    }


    //Сохранение товара в корзину
    @RequestMapping("/saveToCart")
    public String saveProductToCart(@RequestParam("productId") int productId,
                                    @RequestParam(name = "productNumber", defaultValue = "1") int productNumber,
                                    @CookieValue(required = false, name = "USERID") String sessionId,
                                    HttpSession session,
                                    HttpServletResponse response){
        //Проверка ID сессии
        if (sessionId == null){
            response.addCookie(CookieUtil.getRandomUserIdCookie()); //Сгенерировать ID, если его нет
        }
        OrderCart orderCart = null;

        //Поиск корзины
        try{
            orderCart = orderCartDao.getBySessionId(sessionId);
        }
        catch (Exception e){
            System.out.println("Creating new cart");
        }

        //Если корзины нет, создаётся новая
        if(orderCart == null){
            orderCart = new OrderCart();
            orderCart.setUid(sessionId);
        }
        OrderCartItem newItem = null;
        //Поиск элемента в корзине
        try{
            newItem = orderCartItemDao.getItemFromCart(orderCart.getId(), productId);
            newItem.setNumber(newItem.getNumber() + productNumber);
            orderCartItemDao.saveOrderCart(newItem);
            return "redirect:/cart";
        }
        catch (Exception e){
            //Если не найден добавляем новый
            System.out.println("Creating new item");
            newItem = new OrderCartItem(productDao.getById(productId), productNumber);
        }
        orderCart.addItemInCart(newItem);

        //Сохранение корзины
        orderCartDao.saveOrderCart(orderCart);

        return "redirect:/cart";
    }

    //Удаление элемента из корзины
    @RequestMapping("deleteFromCart")
    private String deleteFromCart(@RequestParam("cartItemId") int cartItemId){
        orderCartItemDao.delete(orderCartItemDao.getById(cartItemId));

        return "redirect:/cart";
    }

    //Открытие корзины
    @RequestMapping("/cart")
    private String getCartView(Model model,
                               @CookieValue(required = false, name = "USERID") String sessionId,
                               HttpServletResponse response){
        //Если нет ID сессии, сгенерировать и сохранить в cookie
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


    //Увеличение числа товаров в корзине
    @PostMapping("/incrementItem")
    @ResponseBody
    private ResponseEntity<?> incrementItem(@RequestParam("itemCartId") int itemCartId,
                                         Model model) {
        OrderCartItem item = orderCartItemDao.getById(itemCartId);
        item.setNumber(item.getNumber() + 1);
        orderCartItemDao.saveOrderCart(item);

        return ResponseEntity.ok(new Double[]{item.getNumber(), item.getNumber() * item.getProduct().getCost()});
    }


    //Уменьшение числа товаров в корзине
    @PostMapping("/decrementItem")
    @ResponseBody
    private ResponseEntity<?> decrementItem(@RequestParam("itemCartId") int itemCartId,
                                Model model) {
        OrderCartItem item = orderCartItemDao.getById(itemCartId);
        if (item.getNumber() <= 1){
            orderCartItemDao.delete(item);
            return ResponseEntity.ok("-1");
        }
        else {
            item.setNumber(item.getNumber() - 1);
        }

        orderCartItemDao.saveOrderCart(item);

        return ResponseEntity.ok(new Double[]{item.getNumber(), item.getNumber() * item.getProduct().getCost()});
    }


    //Страница оформления заказа
    @RequestMapping("/ordering")
    private String orderingPage(@RequestParam("orderCartId") int orderCartId,
                                Model model){
        //Получение аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //authentication.setAuthenticated(false);

        System.out.println(authentication.isAuthenticated());

        //Если не авторизован, форма пустая
        if (!(authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken))){
            model.addAttribute("client", new Client());
        }
        //Если авторизован, форма заполняется
        else {
            System.out.println("Пользователь авторизован");
            Client client = userDao.getByUsername(authentication.getName()).getClient();
            model.addAttribute("client", client);
            model.addAttribute("isDisabled", "readonly");
        }

        model.addAttribute("order", new Order());
        model.addAttribute("orderCartId", orderCartId);
        model.addAttribute("orderCart", orderCartDao.getById(orderCartId));
        model.addAttribute("orderSum", 0d);
        model.addAttribute("uid", new UID());
        model.addAttribute("deliveryPrice", metadataDao.getByKey("deliveryPrice").getValue());

        return "shop-pages/shop-ordering-page";

    }

    //Отправка заказа
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
        //Проверка заполнения
        if(clientEmail.equals("")
        || clientName.equals("")
        || clientSurname.equals("")
        || clientPhoneNumber.equals(""))
            throw new RuntimeException("Заполните все значения");

        //Если заказ существует, не сохранять
        try{
            response.addCookie(CookieUtil.getRandomUserIdCookie());
            Order oldOrder = orderDao.getByUid(uid);

            model.addAttribute("order", oldOrder);

            return "shop-pages/shop-order-created-page";
        }
        //Если не существует, создаём
        catch (Exception e){
            try {
                Client client = null;

                //Если клиент не создан, регистрируем
                if(clientId == 0){

                    //Если номер занят, отправка на форму авторизации
                    if (clientDao.isExistsByPhoneNumber(clientPhoneNumber)){
                        model.addAttribute("error", "Данный номер телефона уже используется");
                        return "shop-pages/shop-login-form";
                    }

                    client = new Client(clientName, clientSurname, clientPhoneNumber, clientEmail);

                    String password = PasswordUtil.generatePassword(8);

                    registerClient(client, password, password);
                }
                else {
                    client = clientDao.getById(clientId);
                }

                Order order = new Order();

                order.setOrderCart(orderCartDao.getById(orderCartId));
                order.setClient(client);
                order.setCurrentDate();
                order.setUid(uid);


                //заполнение адреса доставки
                if (shipmentMethod.equals("ship")){
                    order.setShipmentMethod(ShipmentMethods.ship);
                    Address address = new Address(new City(city), new Street(street), houseNumber, flatNumber);
                    order.setAddress(address);
                }
                else order.setShipmentMethod(ShipmentMethods.pickup);

                //отправка письма
                EmailThread emailThread = new EmailThread(order.getClient().getEmail(), order);
                Thread thread = new Thread(emailThread);

                model.addAttribute("order", order);

                //проверка способа оплаты
                if (paymentMethod.equals("cash")){
                    order.setPaymentMethod(PaymentMethods.cash);
                    //orderDao.saveOrUpdate(order);
                    orderDao.persist(order);

                    thread.start();

                    return "shop-pages/shop-order-created-page";
                }
                //если оплата онлайн, открытие страницы оплаты
                else {
                    order.setPaymentMethod(PaymentMethods.card);

                    //orderDao.saveOrUpdate(order);
                    orderDao.persist(order);

                    double deliveryPrice = 0;

                    if(order.getShipmentMethod() == ShipmentMethods.ship)
                        deliveryPrice += Double.parseDouble(metadataDao.getByKey("deliveryPrice").getValue());

                    String apiResponse = Payment.SendRequest(order, deliveryPrice);

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

    //отправка быстрого заказа
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

    //форма авторизации
    @GetMapping("/loginPage")
    public String getLoginPage(){
        System.out.println("Авторизация");

        return "shop-pages/shop-login-form";
    }

    //авторизация
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model){
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
            return "shop-pages/shop-login-form";
        }
    }

    //страница регистрации
    @RequestMapping("/register")
    public String getRegisterPage(Model model){

        model.addAttribute("client", new Client());

        return "shop-pages/shop-register-form";
    }

    //регистрация
    @RequestMapping("/registration")
    public String registerClient(@ModelAttribute Client client,
                                 @RequestParam("password") String password,
                                 @RequestParam("passwordConfirm") String passwordConfirm){

        //проверка пароля
        if(password.equals("")){
            return "redirect:/register?passwordNotProvided";
        }
        if(!password.equals(passwordConfirm)){
            return "redirect:/register?passwordNotMatch";
        }
        //занят ли номер
        if(clientDao.isExistsByPhoneNumber(client.getPhoneNumber())){
            return "redirect:/register?phoneUsed";
        }

        //сохранение клиента
        clientDao.save(client);

        //сохранение пользователя
        User user = new User();
        user.setUsername(client.getPhoneNumber());
        user.setPassword("{bcrypt}" + Encoder.encodePassword(password));
        user.setEnabled((byte) 1);
        user.setClient(client);
        Authority authority = new Authority(user, AuthorityType.USER);
        userDao.save(user);
        authorityDao.save(authority);

        //аутентификация
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //отправка письма
        EmailThread emailThread = new EmailThread(client.getEmail(), client, user, password);
        Thread thread = new Thread(emailThread);
        thread.start();

        return "redirect:/personalPage";
    }

    //страницы с информацией
    @RequestMapping("/documents")
    public String getDocumentsPage(){
        return "shop-pages/shop-documents-page";
    }

    @RequestMapping("/description")
    public String getDescriptionPage(Model model){
        model.addAttribute("description", metadataDao.getByKey("description").getValue());
        return "shop-pages/shop-description-page";
    }

    @RequestMapping("/shipmentInfo")
    public String getShipmentInfoPage(){
        return "shop-pages/shop-shipment-info-page";
    }

    @RequestMapping("/paymentInfo")
    public String getPaymentInfoPage(){
        return "shop-pages/shop-payment-info-page";
    }

    @RequestMapping("/contactInfo")
    public String getContactInfoPage(){
        return "shop-pages/shop-contacts-page";
    }

    @RequestMapping("/testEmail")
    public void testEmail(@RequestParam(value = "email", defaultValue = "maran_chan@vk.com") String email){
        EmailUtil.sendOrderInfo(email, orderDao.getById(170));
    }

    @RequestMapping("/ordercreated")
    public String  testordercreated(Model model){
        model.addAttribute("order", orderDao.getById(170));
        return "shop-pages/shop-order-created-page";
    }
}
