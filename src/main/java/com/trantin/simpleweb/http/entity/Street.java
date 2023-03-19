package com.trantin.simpleweb.http.entity;

import javax.persistence.*;


@Entity
@Table(name = "streets")
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Override
    public String toString() {
        return "Street{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Street() {
    }

    public Street(String name) {
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
