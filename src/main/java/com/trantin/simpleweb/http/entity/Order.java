package com.trantin.simpleweb.http.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "orders")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "order_cart")
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private OrderCart orderCart;

    @JoinColumn(name = "client_id")
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Client client;

    @Column(name = "date")
    private Date orderDate;

    @Column(name = "confirmed")
    private boolean confirmed;

    @JoinColumn(name = "address")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    private Address address;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Column(name = "uid")
    private String uid;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private PaymentMethods paymentMethod;

    @Column(name = "shipment_method")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ShipmentMethods shipmentMethod;

    public Order() {}

    public Order(Client client, String orderDate) {
        //LocalDate ldate = LocalDate.now();

        this.client = client;
        //this.date = (ldate.getDayOfMonth() + "-" + ldate.getMonthValue() + "-" + ldate.getYear());
    }

    public void setCurrentDate(){
        this.orderDate = Date.valueOf(LocalDate.now());
    }

    public double orderSum() {
        double result = 0;

        List<OrderCartItem> list = orderCart.getItems();

        for (int i = 0; i < list.size(); i++) {
            result += orderCart.getItems().get(i).getProduct().getCost() * orderCart.getItems().get(i).getNumber();
        }

        String formatted = String.format("%.2f", result);

        return Double.parseDouble(formatted);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCart=" + orderCart +
                ", client=" + client +
                ", date=" + orderDate +
                ", confirmed=" + confirmed +
                ", address=" + address +
                ", paymentMethod=" + paymentMethod +
                ", shipmentMethod=" + shipmentMethod +
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

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date date) {
        this.orderDate = date;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String isConfirmedStr(){
        if(isConfirmed()){
            return "Доставлен";
        }
        return "В работе";
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

    public PaymentMethods getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentMethodStr() {
        if(paymentMethod == PaymentMethods.cash)
            return "Наличная оплата";
        else if (paymentMethod == PaymentMethods.card)
            return "Безналичная оплата";

        return "Неопределено";
    }

    public void setPaymentMethod(PaymentMethods paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShipmentMethods getShipmentMethod() {
        return shipmentMethod;
    }

    public String getShipmentMethodStr() {
        if(shipmentMethod == ShipmentMethods.ship)
            return "Доставка";
        else if (shipmentMethod == ShipmentMethods.pickup)
            return "Самовывоз";

        return "Неопределено";
    }

    public void setShipmentMethod(ShipmentMethods shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    /*public void setPaymentMethodEnum(PaymentMethods payment_method) {
        switch (payment_method){
            case Cash:
                this.paymentMethod = "cash";
                break;
            case Card:
                this.paymentMethod = "card";
                break;
        }
    }*/
}
