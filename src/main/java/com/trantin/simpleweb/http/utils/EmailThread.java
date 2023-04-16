package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.entity.Order;

public class EmailThread implements Runnable{

    private String email;

    private Order order;

    public EmailThread(String email, Order order){
        this.email = email;
        this.order = order;
    }

    @Override
    public void run() {
        EmailUtil.sendOrderInfo(email, order);
    }
}
