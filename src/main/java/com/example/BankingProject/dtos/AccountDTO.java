package com.example.BankingProject.dtos;

import javax.persistence.Embedded;
import java.math.BigDecimal;

public class AccountDTO {

    private Long id;

    @Embedded
    private BigDecimal balance;
    private BigDecimal minBalance;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private String accountType;

    public AccountDTO() {

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
}
