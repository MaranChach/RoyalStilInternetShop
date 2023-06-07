package com.trantin.simpleweb.http.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TableColumn {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "data_type")
    private String data_type;

    @Column(name = "is_required")
    private String is_required;

    public TableColumn() {
    }

    public TableColumn(String name, String data_type, String is_nullable) {
        this.name = name;
        this.data_type = data_type;
        setIs_required(is_nullable);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getIs_required() {
        return is_required;
    }

    public void setIs_required(String is_nullable) {
        if (is_nullable.equals("YES"))
            this.is_required = "N";
        else if(is_nullable.equals("NO"))
            this.is_required = "Y";
        else
            this.is_required = "Y";

    }
}
