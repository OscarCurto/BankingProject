package com.example.BankingProject.controllers.accounts;

import com.example.BankingProject.controllers.accounts.interfaces.CheckingAccountControllerInterface;
import com.example.BankingProject.services.accounts.interfaces.CheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingAccountController implements CheckingAccountControllerInterface {

    @Autowired
    CheckingAccountServiceInterface checkingAccountServiceInterface;
}
