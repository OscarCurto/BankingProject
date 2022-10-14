package com.example.BankingProject.services.accounts;

import com.example.BankingProject.repositories.accounts.CheckingAccountRepository;
import com.example.BankingProject.services.accounts.interfaces.CheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingAccountService implements CheckingAccountServiceInterface {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;
}
