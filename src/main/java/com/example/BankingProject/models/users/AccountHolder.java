package com.example.BankingProject.models.users;

import com.example.BankingProject.embedables.Address;
import com.example.BankingProject.models.accounts.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AccountHolder extends User {

    private String mail;

    private LocalDate dateOfBirth;

    private String phone;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "street_mail")),
            @AttributeOverride(name = "city", column = @Column(name = "city_mail")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "postal_code_mail")),
            @AttributeOverride(name = "provinceState", column = @Column(name = "province_state_mail")),
            @AttributeOverride(name = "country", column = @Column(name = "country_mail"))
    })
    private Address mailingAddress;


    @OneToMany(mappedBy = "primaryAccountHolder")
    @JsonIgnore
    private List<Account> primaryAccountHolderList;

    @OneToMany(mappedBy = "secondaryAccountHolder")
    @JsonIgnore
    private List<Account> secondaryAccountHolderList;

    public AccountHolder() {

    }

    public AccountHolder(String name, String mail, LocalDate dateOfBirth, String phone, Address address, Address mailingAddress, List<Account> primaryAccountHolderList, List<Account> secondaryAccountHolderList) {
        super(name);
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.mailingAddress = mailingAddress;
        this.primaryAccountHolderList = primaryAccountHolderList;
        this.secondaryAccountHolderList = secondaryAccountHolderList;
    }

    public AccountHolder(String userName, String password) {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<Account> getPrimaryAccountHolderList() {
        return primaryAccountHolderList;
    }

    public void setPrimaryAccountHolderList(List<Account> primaryAccountHolderList) {
        this.primaryAccountHolderList = primaryAccountHolderList;
    }

    public List<Account> getSecondaryAccountHolderList() {
        return secondaryAccountHolderList;
    }

    public void setSecondaryAccountHolderList(List<Account> secondaryAccountHolderList) {
        this.secondaryAccountHolderList = secondaryAccountHolderList;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
