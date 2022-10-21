package com.example.BankingProject.repositories.transactions;

import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount(Account account);
}
