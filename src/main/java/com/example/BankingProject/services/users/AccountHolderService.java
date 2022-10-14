package com.example.BankingProject.services.users;

import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService implements AccountServiceInterface {

    @Autowired
    AccountHolderRepository accountHolderRepository;
}
