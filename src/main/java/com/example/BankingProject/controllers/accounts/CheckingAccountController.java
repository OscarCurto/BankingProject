package com.example.BankingProject.controllers.accounts;

import com.example.BankingProject.controllers.accounts.interfaces.CheckingAccountControllerInterface;
import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.services.accounts.interfaces.CheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckingAccountController implements CheckingAccountControllerInterface {

    @Autowired
    CheckingAccountServiceInterface checkingAccountServiceInterface;

    @GetMapping("/checking")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CheckingAccount> showCheckingAccounts() {
        return checkingAccountServiceInterface.showCheckingAccounts();
    }
}
