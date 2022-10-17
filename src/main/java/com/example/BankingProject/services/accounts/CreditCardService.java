package com.example.BankingProject.services.accounts;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.models.accounts.CreditCard;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.CreditCardRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    //Use this method to show a list of creditCardAccounts
    public List<CreditCard> showCreditCardAccounts() {
        return creditCardRepository.findAll();
    }

    //Use this method to create a new Credit Card account
    public CreditCard createCreditCardAccount(AccountDTO accountDTO) {
        if (accountHolderRepository.findById(accountDTO.getPrimaryAccountHolder()).isPresent()) {
            AccountHolder primaryAccountHolder = accountHolderRepository.findById(accountDTO.getPrimaryAccountHolder()).get();
            AccountHolder secondaryAccountHolder = null;

            if (accountDTO.getSecondaryAccountHolder() != null && accountHolderRepository.findById(accountDTO.getSecondaryAccountHolder()).isPresent()) {
                secondaryAccountHolder = accountHolderRepository.findById(accountDTO.getSecondaryAccountHolder()).get();
            }

            CreditCard creditCard = new CreditCard(
                    accountDTO.getBalance(),
                    primaryAccountHolder,
                    secondaryAccountHolder,
                    accountDTO.getPenaltyFee(),
                    accountDTO.getCreditLimit(),
                    accountDTO.getInterestRate(),
                    accountDTO.getCreationDate(),
                    accountDTO.getLastAddedInterestRate()
            );
            return creditCardRepository.save(creditCard);
        }
        throw new IllegalArgumentException("Primary Holder does not exist" );
    }
}
