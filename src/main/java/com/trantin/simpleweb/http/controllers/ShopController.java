package com.trantin.simpleweb.http.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {

    @RequestMapping("/")
    public String getMainPage(){
        return "shop-main-page";
    }

    @RequestMapping("/catalog")
    public String getCatalogPage(){
        return "shop-catalog-page";
    }

    @RequestMapping("/category")
    public String getCategoryPage(){
        return "shop-category-page";
    }

    @RequestMapping("/product")
    public String getProductPage(){
        return "shop-product-page";
    }
}
