package com.example.BankingProject.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdPartyUser extends User{

    public ThirdPartyUser() {

    }

    public ThirdPartyUser(String name) {
        super(name);
    }

    public ThirdPartyUser(String userName, String password, String hashedKey) {
    }
}
