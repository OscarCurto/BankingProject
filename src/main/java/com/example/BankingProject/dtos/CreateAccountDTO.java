package com.example.BankingProject.dtos;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.users.AccountHolder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateAccountDTO {

    private Long id;
    private Money balance;
    private AccountHolder primaryAccountHolder;
    private AccountHolder secondaryAccountHolder;
    private Status status;
    @DecimalMin(value = "100")
    private BigDecimal minBalance;
    @DecimalMax(value = "0.5")
    private BigDecimal interestRate;
    private LocalDate lastInterestDay;
    private String accountType;
    private BigDecimal penaltyFee;
    private LocalDate creationDate;
    @Range(min = 100, max = 100000)
    private BigDecimal creditLimit;
    private LocalDate lastAddedInterestRate;

    public CreateAccountDTO(Long id, Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, Status status,
                            BigDecimal minBalance, BigDecimal interestRate, LocalDate lastInterestDay, String accountType) {
        this.id = id;
        this.balance = balance;
        this.primaryAccountHolder = primaryAccountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
        this.status = status;
        this.minBalance = minBalance;
        this.interestRate = interestRate;
        this.lastInterestDay = lastInterestDay;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryAccountHolder() {
        return primaryAccountHolder;
    }

    public void setPrimaryAccountHolder(AccountHolder primaryAccountHolder) {
        this.primaryAccountHolder = primaryAccountHolder;
    }

    public AccountHolder getSecondaryAccountHolder() {
        return secondaryAccountHolder;
    }

    public void setSecondaryAccountHolder(AccountHolder secondaryAccountHolder) {
        this.secondaryAccountHolder = secondaryAccountHolder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getLastInterestDay() {
        return lastInterestDay;
    }

    public void setLastInterestDay(LocalDate lastInterestDay) {
        this.lastInterestDay = lastInterestDay;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public LocalDate getLastAddedInterestRate() {
        return lastAddedInterestRate;
    }

    public void setLastAddedInterestRate(LocalDate lastAddedInterestRate) {
        this.lastAddedInterestRate = lastAddedInterestRate;
    }
}
