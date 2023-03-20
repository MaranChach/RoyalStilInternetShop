package com.trantin.simpleweb.http;

import com.trantin.simpleweb.http.configuration.MyConfig;
import com.trantin.simpleweb.http.configuration.MyWebInitializer;
import com.trantin.simpleweb.http.controllers.CRMController;
import com.trantin.simpleweb.http.controllers.ShopController;
import com.trantin.simpleweb.http.dao.*;
import com.trantin.simpleweb.http.entity.*;
import com.trantin.simpleweb.http.services.UnitDaoService;
import com.trantin.simpleweb.http.utils.ClassUML;
import com.trantin.simpleweb.http.utils.Validator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {

    public static void main(String[] args) {

        String s = Validator.trimImageUrl("[url=https://postimg.cc/S2m57c49][img]https://i.postimg.cc/S2m57c49/29-11-2022-203649.jpg[/img][/url]");

        System.out.println(s);


        /*ClassUML classUML = new ClassUML();



        classUML.printSummary(MyConfig.class);
        //classUML.printSummary(MyWebInitializer.class);
        classUML.printSummary(CRMController.class);
        classUML.printSummary(ShopController.class);
        classUML.printSummary(CategoryDao.class);
        classUML.printSummary(ClientDao.class);
        classUML.printSummary(DetailsAttribute.class);
        classUML.printSummary(EmployeeDaoImplement.class);
        classUML.printSummary(ManufacturerDao.class);
        classUML.printSummary(OrderCartDao.class);
        classUML.printSummary(OrderDao.class);
        classUML.printSummary(ProductDao.class);
        classUML.printSummary(UnitDao.class);
        classUML.printSummary(Category.class);
        classUML.printSummary(Client.class);
        classUML.printSummary(Details.class);
        classUML.printSummary(DetailsAttribute.class);
        classUML.printSummary(DetailsParameter.class);
        classUML.printSummary(Employee.class);
        classUML.printSummary(Manufacturer.class);
        classUML.printSummary(Order.class);
        classUML.printSummary(OrderCart.class);
        classUML.printSummary(OrderCartItem.class);
        classUML.printSummary(Product.class);
        classUML.printSummary(Unit.class);*/




    }
}
