package com.example.BankingProject.models.accounts;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class StudentCheckingAccount extends Account {

    public StudentCheckingAccount() {

    }


    public StudentCheckingAccount(Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, LocalDate creationDate, Status status) {
        super(balance, primaryAccountHolder, secondaryAccountHolder, creationDate, status);
    }

    public StudentCheckingAccount(BigDecimal balance, AccountHolder accountHolder) {

    }

    public StudentCheckingAccount(BigDecimal balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
    }
}
