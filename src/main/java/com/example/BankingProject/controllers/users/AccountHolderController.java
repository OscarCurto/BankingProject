package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.accounts.interfaces.AccountControllerInterface;
import com.example.BankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderController implements AccountControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;
}
