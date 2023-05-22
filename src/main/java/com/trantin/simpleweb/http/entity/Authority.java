package com.trantin.simpleweb.http.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @JoinColumn(name = "username")
    @ManyToOne
    private User username;


    @Id
    @Column(name = "authority")
    private String authority;

    public Authority() {
    }

    public Authority(User username, AuthorityType authorityType) {
        this.username = username;
        setAuthority(authorityType);
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User user) {
        this.username = user;
    }

    public String getAuthorityStr() {
        return authority;
    }

    private AuthorityType getAuthorityType(){
        if(authority.equals("ROLE_USER")){
            return AuthorityType.USER;
        }
        else if (authority.equals("ROLE_EMPLOYEE")) {
            return AuthorityType.EMPLOYEE;
        }
        else{
            return null;
        }
    }

    public void setAuthority(AuthorityType type) {

        switch (type) {
            case USER:
                this.authority = "ROLE_USER";
                break;
            case EMPLOYEE:
                this.authority = "ROLE_EMPLOYEE";
                break;
        }


    }
}
