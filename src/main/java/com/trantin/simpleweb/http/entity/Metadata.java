package com.trantin.simpleweb.http.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "metadata")
public class Metadata {

    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Override
    public String toString() {
        return "Metadata{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Metadata() {
    }

    public Metadata(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
