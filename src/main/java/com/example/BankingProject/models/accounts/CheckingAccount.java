package com.example.BankingProject.models.accounts;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CheckingAccount extends Account {

    @Embedded
    private Money minimumBalance = new Money(BigDecimal.valueOf(250L));

    private BigDecimal monthlyMaintenanceFee = new BigDecimal(0.01);

    private LocalDate lastInterestDay;

    public CheckingAccount() {

    }

    public CheckingAccount(Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, LocalDate creationDate, Status status, Money minimumBalance, BigDecimal monthlyMaintenanceFee, LocalDate lastInterestDay) {
        super(balance, primaryAccountHolder, secondaryAccountHolder, creationDate, status);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.lastInterestDay = lastInterestDay;
    }

    public CheckingAccount(BigDecimal balance, AccountHolder accountHolder) {

    }

    public CheckingAccount(BigDecimal balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
    }

    //ESTE
    public CheckingAccount(BigDecimal amount, AccountHolder primaryAccountHolder, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getLastInterestDay() {
        return lastInterestDay;
    }

    public void setLastInterestDay(LocalDate lastInterestDay) {
        this.lastInterestDay = lastInterestDay;
    }
}