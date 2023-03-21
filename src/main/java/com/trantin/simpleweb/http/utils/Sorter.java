package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class Sorter {

    public static List<Order> getSortedByConfirmOrders(List<Order> orders, boolean confirmed){
        List<Order> result = new ArrayList<>();

        for (Order order : orders) {
            if (confirmed) {
                if(order.isConfirmed()){
                    result.add(order);
                }
            }
            else {
                if(!order.isConfirmed()){
                    result.add(order);
                }
            }
        }

        return result;
    }

}
