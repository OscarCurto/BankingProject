package com.example.BankingProject.services.accounts;

import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;

}
