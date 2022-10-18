package com.example.BankingProject.dtos;

import com.example.BankingProject.enums.Status;

import javax.persistence.Embedded;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDTO {

    private Long id;
    private Long accountHolderId;
    private BigDecimal balance;
    private BigDecimal minBalance;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private String accountType;
    private BigDecimal amount;
    private Status status;
    private Long primaryAccountHolder;
    private Long secondaryAccountHolder;
    private BigDecimal penaltyFee;
    private LocalDate creationDate;
    private BigDecimal monthlyMaintenanceFee;
    private LocalDate lastAddedInterestRate;

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

    public AccountDTO(Long id, Long accountHolderId, BigDecimal balance, BigDecimal minBalance, BigDecimal creditLimit, BigDecimal interestRate,
                      String accountType, BigDecimal amount, Status status, Long primaryAccountHolder, Long secondaryAccountHolder,
                      BigDecimal penaltyFee, LocalDate creationDate, BigDecimal monthlyMaintenanceFee, LocalDate lastAddedInterestRate) {
        this.id = id;
        this.accountHolderId = accountHolderId;
        this.balance = balance;
        this.minBalance = minBalance;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.accountType = accountType;
        this.amount = amount;
        this.status = status;
        this.primaryAccountHolder = primaryAccountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.lastAddedInterestRate = lastAddedInterestRate;
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

    public Long getPrimaryAccountHolder() {
        return primaryAccountHolder;
    }

    public void setPrimaryAccountHolder(Long primaryAccountHolder) {
        this.primaryAccountHolder = primaryAccountHolder;
    }

    public Long getSecondaryAccountHolder() {
        return secondaryAccountHolder;
    }

    public void setSecondaryAccountHolder(Long secondaryAccountHolder) {
        this.secondaryAccountHolder = secondaryAccountHolder;
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

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getLastAddedInterestRate() {
        return lastAddedInterestRate;
    }

    public void setLastAddedInterestRate(LocalDate lastAddedInterestRate) {
        this.lastAddedInterestRate = lastAddedInterestRate;
    }
}
