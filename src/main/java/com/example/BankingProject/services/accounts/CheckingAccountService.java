package com.example.BankingProject.services.accounts;

import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.repositories.accounts.CheckingAccountRepository;
import com.example.BankingProject.services.accounts.interfaces.CheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingAccountService implements CheckingAccountServiceInterface {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    //Use this method to show a list of checkingAccount
    public List<CheckingAccount> showCheckingAccounts() {
        return checkingAccountRepository.findAll();
    }
}
