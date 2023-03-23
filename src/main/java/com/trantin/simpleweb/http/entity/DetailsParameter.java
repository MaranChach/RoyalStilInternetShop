package com.trantin.simpleweb.http.entity;


import javax.persistence.*;

@Entity
@Table(name = "details_parameters")
public class DetailsParameter {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "unit")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Unit unit;


    @Override
    public String toString() {
        return "DetailsParameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit.getName() +
                '}';
    }

    public DetailsParameter() {
    }

    public DetailsParameter(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
