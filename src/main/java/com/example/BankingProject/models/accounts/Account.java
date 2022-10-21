package com.example.BankingProject.models.accounts;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.transactions.Transaction;
import com.example.BankingProject.models.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Embedded
    private Money balance;

    @ManyToOne
    @JoinColumn(name = "account_holder_id")
    private AccountHolder primaryAccountHolder;

    @ManyToOne
    @JoinColumn(name = "secondary_account_holder_id")
    private AccountHolder secondaryAccountHolder;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "currency_fee")),
            @AttributeOverride(name = "amount", column = @Column(name = "amount_fee"))
    })
    private final Money penaltyFee = new Money(BigDecimal.valueOf(40L));

    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;

    public Account() {

    }

    public Account(Money balance, AccountHolder primaryAccountHolder, AccountHolder secondaryAccountHolder, LocalDate creationDate, Status status) {
        this.balance = balance;
        this.primaryAccountHolder = primaryAccountHolder;
        this.secondaryAccountHolder = secondaryAccountHolder;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
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

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
