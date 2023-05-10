package com.trantin.simpleweb.http.entity;


import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "city")
    @ManyToOne(cascade = {CascadeType.ALL})
    private City city;

    @JoinColumn(name = "street")
    @ManyToOne(cascade = {CascadeType.ALL})
    private Street street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "flat_number")
    private int flatNumber;


    @Override
    public String toString() {
        return  "г. " + city +
                " ул. " + street +
                " д. " + houseNumber +
                " кв. " + flatNumber;
    }

    public Address() {
    }

    public Address(City city, Street street, String houseNumber, int flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public String  getSummary() {
        return "г. " + getCity().getName() + " ул. " + getStreet().getName() + " д. " + getHouseNumber() + " кв. " + getFlatNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }
}
