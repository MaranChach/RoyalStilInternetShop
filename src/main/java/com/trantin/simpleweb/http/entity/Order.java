package com.trantin.simpleweb.http.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "order_cart")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OrderCart orderCart;

    @JoinColumn(name = "client_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @Column(name = "date")
    private Date date;

    @Column(name = "confirmed")
    private boolean confirmed;

    @JoinColumn(name = "address")
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;


    public Order() {}

    public Order(Client client, String date) {
        //LocalDate ldate = LocalDate.now();

        this.client = client;
        //this.date = (ldate.getDayOfMonth() + "-" + ldate.getMonthValue() + "-" + ldate.getYear());
    }

    public void setCurrentDate(){
        this.date = Date.valueOf(LocalDate.now());
    }

    public double orderSum(){
        System.out.println("работай сука");

        double result = 0;

        List<OrderCartItem> list = orderCart.getItems();

//        for (int i = 0; i < list.size(); i++) {
//            result += orderCart.getItems().get(i).getProduct().getCost();
//        }

        System.out.println(result);

        return result;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCart=" + orderCart +
                ", client=" + client +
                ", date=" + date +
                ", confirmed=" + confirmed +
                ", address=" + address +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderCart getOrderCart() {
        return orderCart;
    }

    public void setOrderCart(OrderCart orderCart) {
        this.orderCart = orderCart;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String isConfirmedStr(){
        if(isConfirmed()){
            return "Да";
        }
        return "Нет";
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
