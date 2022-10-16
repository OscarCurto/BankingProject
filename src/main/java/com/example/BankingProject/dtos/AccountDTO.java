package com.example.BankingProject.dtos;

import com.example.BankingProject.enums.Status;

import javax.persistence.Embedded;
import java.math.BigDecimal;

public class AccountDTO {

    private Long id;
    private Long accountHolderId;
    @Embedded
    private BigDecimal balance;
    private BigDecimal minBalance;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private String accountType;
    private BigDecimal amount;
    private Status status;

    public AccountDTO(Status status) {
        this.status = status;
    }

    public AccountDTO(BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public AccountDTO(BigDecimal balance, String accountType) {
        this.balance = balance;
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(Long accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
