package com.example.BankingProject.models.accounts;

import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class StudentCheckingAccount extends Account {

    public StudentCheckingAccount() {

    }

    //Use this constructor to create a new Student Checking on service
    public StudentCheckingAccount(BigDecimal balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, BigDecimal penaltyFee, LocalDate creationDate, Status status) {
    }
}
