package com.trantin.simpleweb.http.entity;

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

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private PaymentMethods paymentMethod;

    @Column(name = "shipment_method")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ShipmentMethods shipmentMethod;

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
