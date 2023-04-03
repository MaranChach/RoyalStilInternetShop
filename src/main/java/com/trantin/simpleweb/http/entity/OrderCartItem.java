package com.trantin.simpleweb.http.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.trantin.simpleweb.http.entity.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_cart_item")
public class OrderCartItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "product_id")
    @ManyToOne()
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Product product;

    @Column(name = "number")
    private double number;

    @JoinColumn(name = "order_cart_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private OrderCart orderCart;


    @Override
    public String toString() {
        return "OrderCartItem{" +
                "id=" + id +
                ", product=" + product +
                ", number=" + number +
                '}';
    }

    public OrderCartItem() {}

    public OrderCartItem(Product product, double number) {
        this.product = product;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public OrderCart getOrderCart() {
        return orderCart;
    }

    public void setOrderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
    }
}
