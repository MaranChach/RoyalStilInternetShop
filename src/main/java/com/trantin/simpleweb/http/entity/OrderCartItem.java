package com.trantin.simpleweb.http.entity;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "number")
    private double number;

    @JoinColumn(name = "order_cart_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
