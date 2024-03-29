package com.trantin.simpleweb.http.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "details_attribute")
public class DetailsAttribute {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "parameter_id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private DetailsParameter parameter;

    @Column(name = "value")
    private double value;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Unit unit;

    @JoinColumn(name = "details_id")
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private Details details;

    public DetailsAttribute(){}

    public DetailsAttribute(DetailsParameter name, double value, Unit unit){
        this.parameter = name;
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "DetailsAttribute{" +
                "id=" + id +
                ", name='" + parameter + '\'' +
                ", value=" + value +
                ", unit=" + unit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DetailsParameter getParameter() {
        return parameter;
    }

    public void setParameter(DetailsParameter name) {
        this.parameter = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
