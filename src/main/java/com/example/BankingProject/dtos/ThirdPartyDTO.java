package com.example.BankingProject.dtos;

public class ThirdPartyDTO {

    private String name;
    private String password;
    private String hashedKey;

    public ThirdPartyDTO(String name, String password, String hashedKey) {
        this.name = name;
        this.password = password;
        this.hashedKey = hashedKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
