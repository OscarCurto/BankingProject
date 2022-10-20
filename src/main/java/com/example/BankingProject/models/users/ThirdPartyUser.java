package com.example.BankingProject.models.users;

import javax.persistence.Entity;

@Entity
public class ThirdPartyUser extends User{

    private String hashedKey;
    public ThirdPartyUser() {

    }

    public ThirdPartyUser(String name, String password, String hashedKey) {
        super(name, password);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
