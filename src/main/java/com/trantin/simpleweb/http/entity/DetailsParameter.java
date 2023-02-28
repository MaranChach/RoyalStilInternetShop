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


    @Override
    public String toString() {
        return "DetailsParameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public DetailsParameter() {
    }

    public DetailsParameter(String name) {
        this.name = name;
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
}
