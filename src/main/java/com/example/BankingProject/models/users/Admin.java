package com.example.BankingProject.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    private String password;

    public Admin() {

    }

    public Admin(String name) {
        super(name);
    }

    public Admin(String name, String password) {
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
