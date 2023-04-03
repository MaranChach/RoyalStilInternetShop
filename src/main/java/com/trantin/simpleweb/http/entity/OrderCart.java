package com.trantin.simpleweb.http.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<OrderCartItem> items;

    @Column(name = "session_id")
    private String uid;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<OrderCartItem> getItems() {
        return items;
    }

    public void setItems(List<OrderCartItem> items) {
        this.items = items;
    }
}
