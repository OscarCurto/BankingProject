package com.example.BankingProject.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdPartyUser extends User{

    private String password;
    public ThirdPartyUser() {

    }

    public ThirdPartyUser(String name) {
        super(name);
    }

    public ThirdPartyUser(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
