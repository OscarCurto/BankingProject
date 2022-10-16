package com.example.BankingProject.dtos;

import com.example.BankingProject.embedables.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public AccountHolderDTO(Long senderAccountId, Long receiverAccountId, BigDecimal amount) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
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
}
