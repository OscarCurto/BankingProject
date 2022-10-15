package com.example.BankingProject.models.users;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {

    }

    public Admin(String name) {
        super(name);
    }
}
