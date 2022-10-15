package com.example.BankingProject.models.accounts;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditCard extends Account{

    @Embedded
    private Money creditLimit = new Money(BigDecimal.valueOf(100L));

    private BigDecimal interestRate = new BigDecimal(0.2);

    private LocalDate lastInterestDay;

    public CreditCard() {

    }

    public CreditCard(Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, LocalDate creationDate, Status status, Money creditLimit, BigDecimal interestRate, LocalDate lastInterestDay) {
        super(balance, primaryAccountHolder, secondaryAccountHolder, creationDate, status);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.lastInterestDay = lastInterestDay;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
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
