package com.trantin.simpleweb.http.entity;


import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private Double number;

    @Column(name = "cost")
    private double cost;

    @JoinColumn(name = "unit_id")
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Unit unit;

    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Category category;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "details_id")
    @ManyToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    private Details details;

    @JoinColumn(name = "manufacturer_id")
    @ManyToOne
    private Manufacturer manufacturer;

    public Product() {}

    public Product(String name, Double number, Double cost, Unit unit, Category category, String description, Details details) {
        this.name = name;
        this.number = number;
        this.unit = unit;
        this.category = category;
        this.description = description;
        this.details = details;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                ", unit=" + unit.getName() +
                ", category=" + category.getName() +
                ", description='" + description + '\'' +
                ", details=" + details +
                ", manufacturer=" + manufacturer.getName() +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
