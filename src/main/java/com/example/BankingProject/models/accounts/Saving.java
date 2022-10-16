package com.example.BankingProject.models.accounts;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Saving extends Account{

    @Embedded
    private Money minBalance = new Money(BigDecimal.valueOf(1000L));

    private BigDecimal interestRate = new BigDecimal(0.0025);

    private LocalDate lastInterestDay;

    public Saving() {

    }

    public Saving(Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, LocalDate creationDate, Status status, Money minBalance, BigDecimal interestRate, LocalDate lastInterestDay) {
        super(balance, primaryAccountHolder, secondaryAccountHolder, creationDate, status);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
        this.lastInterestDay = lastInterestDay;
    }

    public Saving(BigDecimal balance, AccountHolder accountHolder) {

    }

    public Money getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Money minBalance) {
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
}
