package com.example.BankingProject.controllers.accounts;

import com.example.BankingProject.controllers.accounts.interfaces.CreditCardControllerInterface;
import com.example.BankingProject.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController implements CreditCardControllerInterface {

    @Autowired
    CreditCardServiceInterface creditCardServiceInterface;
}
