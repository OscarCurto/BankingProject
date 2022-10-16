package com.example.BankingProject.services.accounts;

import com.example.BankingProject.models.accounts.CreditCard;
import com.example.BankingProject.repositories.accounts.CreditCardRepository;
import com.example.BankingProject.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

    //Use this method to show a list of creditCardAccounts
    public List<CreditCard> showCreditCardAccounts() {
        return creditCardRepository.findAll();
    }
}
