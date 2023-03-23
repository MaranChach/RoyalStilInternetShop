package com.trantin.simpleweb.http.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "details")
public class Details {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "details", fetch = FetchType.EAGER)
    private List<DetailsAttribute> attributes;


    public void addAttribute(DetailsAttribute attribute){
        if(attributes == null)
            attributes = new ArrayList<>();

        this.attributes.add(attribute);
        attribute.setDetails(this);
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    public Details() {
    }

    public Details(List<DetailsAttribute> attributes) {
        this.attributes = attributes;
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

    public List<DetailsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DetailsAttribute> attributes) {
        this.attributes = attributes;
    }
}
