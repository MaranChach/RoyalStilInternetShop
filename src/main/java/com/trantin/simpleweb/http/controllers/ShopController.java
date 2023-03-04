package com.trantin.simpleweb.http.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

    @RequestMapping("/")
    public String getMainPage(){
        return "shop-main-page";
    }

}
