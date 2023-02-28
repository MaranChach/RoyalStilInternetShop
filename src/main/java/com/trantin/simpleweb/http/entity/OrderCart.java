package com.trantin.simpleweb.http.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_cart")
public class OrderCart {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "orderCart", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<OrderCartItem> items;


    public void addItemInCart(OrderCartItem cartItem){
        if(this.items == null)
            this.items = new ArrayList<>();

        cartItem.setOrderCart(this);

        items.add(cartItem);
    }

    @Override
    public String toString() {
        return "OrderCart{" +
                "id=" + id +
                ", items=" + items +
                '}';
    }

    public OrderCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderCartItem> getItems() {
        return items;
    }

    public void setItems(List<OrderCartItem> items) {
        this.items = items;
    }
}
