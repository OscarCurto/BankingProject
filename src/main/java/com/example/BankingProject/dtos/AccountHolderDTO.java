package com.example.BankingProject.dtos;

import com.example.BankingProject.embedables.Address;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AccountHolderDTO {

    private Long senderAccountId;
    private Long receiverAccountId;
    private String name;
    private String mail;
    private String phone;
    private LocalDate birthDate;
    private String accountType;
    private Money initialBalance;
    private BigDecimal amount;
    private String userName;
    private String password;
    private List<Account> primaryAccountHolderList;
    private List<Account> secondaryAccountHolderList;
    private Address primaryHolderAccountAddress;
    private Address secondaryAccountHolderAddress;
    private String HashedKey;

    public AccountHolderDTO(Long senderAccountId, Long receiverAccountId, BigDecimal amount) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public AccountHolderDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Money getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Money initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Address getPrimaryHolderAccountAddress() {
        return primaryHolderAccountAddress;
    }

    public void setPrimaryHolderAccountAddress(Address primaryHolderAccountAddress) {
        this.primaryHolderAccountAddress = primaryHolderAccountAddress;
    }

    public Address getSecondaryAccountHolderAddress() {
        return secondaryAccountHolderAddress;
    }

    public void setSecondaryAccountHolderAddress(Address secondaryAccountHolderAddress) {
        this.secondaryAccountHolderAddress = secondaryAccountHolderAddress;
    }

    public String getHashedKey() {
        return HashedKey;
    }

    public void setHashedKey(String hashedKey) {
        HashedKey = hashedKey;
    }
}
