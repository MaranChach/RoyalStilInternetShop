package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.entity.Client;
import com.trantin.simpleweb.http.entity.Order;
import com.trantin.simpleweb.http.entity.User;

public class EmailThread implements Runnable{

    private String email;
    private Order order;
    private Client client;
    private User user;
    private String decryptedPassword;

    public EmailThread(String email, Order order){
        this.email = email;
        this.order = order;
    }

    public EmailThread(String email, Client client, User user, String decryptedPassword){
        this.email = email;
        this.client = client;
        this.user = user;
        this.decryptedPassword = decryptedPassword;
    }

    @Override
    public void run() {
        if(order == null){
            EmailUtil.sendAccountInfo(this.email, this.client, this.user, this.decryptedPassword);
        }
        else {
            EmailUtil.sendOrderInfo(this.email, this.order);
        }
    }
}
