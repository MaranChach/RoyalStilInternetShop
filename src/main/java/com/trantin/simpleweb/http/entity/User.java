package com.trantin.simpleweb.http.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private byte enabled;

    @OneToMany(mappedBy = "username", cascade = CascadeType.ALL)
    private List<Authority> authority;

    @JoinColumn(name = "client_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Client client;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        if (enabled >= 1)
            this.enabled = 1;
        else
            this.enabled = 0;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public List<Authority> getAuthorities() {
        return authority;
    }

    public void setItems(List<Authority> items) {
        this.authority = items;
    }
}
