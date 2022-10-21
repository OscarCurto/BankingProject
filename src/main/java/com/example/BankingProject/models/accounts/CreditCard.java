package com.example.BankingProject.models.accounts;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CreditCard extends Account{

    @Embedded
    private Money creditLimit = new Money(BigDecimal.valueOf(100L));

    @DecimalMin(value = "0.1")
    @DecimalMax(value = "0.2")
    private BigDecimal interestRate = new BigDecimal(0.2);

    private LocalDate lastInterestDay;

    public CreditCard() {

    }

    //Use this constructor to create a new Credit Card on service
    public CreditCard(Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, BigDecimal penaltyFee, BigDecimal creditLimit, BigDecimal interestRate, LocalDate creationDate, LocalDate lastAddedInterestRate) {
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
