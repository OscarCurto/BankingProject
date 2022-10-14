package com.example.BankingProject.services.accounts;

import com.example.BankingProject.repositories.accounts.CreditCardRepository;
import com.example.BankingProject.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;
}
