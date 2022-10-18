package com.example.BankingProject.models.transactions;

import com.example.BankingProject.models.accounts.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String transactionType;
    private long idSender;
    private long idReceiver;
    private BigDecimal amount;
    private LocalDateTime dateTransaction;
    @ManyToOne
    Account account;

    public Transaction() {

    }

    public Transaction(String transactionType, long idSender, long idReceiver, BigDecimal amount, LocalDateTime dateTransaction, Account account) {
        this.transactionType = transactionType;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.amount = amount;
        this.dateTransaction = dateTransaction;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getIdSender() {
        return idSender;
    }

    public void setIdSender(long idSender) {
        this.idSender = idSender;
    }

    public long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(long idReceiver) {
        this.idReceiver = idReceiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
