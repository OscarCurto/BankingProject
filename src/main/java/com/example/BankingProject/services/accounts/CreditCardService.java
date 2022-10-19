package com.example.BankingProject.services.accounts;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.dtos.CreateAccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.CreditCard;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.CreditCardRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.interfaces.CreditCardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    //Use this method to create a new Credit Card account
    public CreditCard createCreditCardAccount(CreateAccountDTO createAccountDTO) {
        if (accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
            AccountHolder primaryAccountHolder = accountHolderRepository.findById(createAccountDTO.getId()).get();
            AccountHolder secondaryAccountHolder = null;

            if (createAccountDTO.getSecondaryAccountHolder() != null && accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
                secondaryAccountHolder = accountHolderRepository.findById(createAccountDTO.getId()).get();
            }

            CreditCard creditCard = new CreditCard(
                    createAccountDTO.getBalance(),
                    primaryAccountHolder,
                    secondaryAccountHolder,
                    createAccountDTO.getPenaltyFee(),
                    createAccountDTO.getCreditLimit(),
                    createAccountDTO.getInterestRate(),
                    createAccountDTO.getCreationDate(),
                    createAccountDTO.getLastAddedInterestRate()
            );
            return creditCardRepository.save(creditCard);
        }
        throw new IllegalArgumentException("Primary Holder does not exist");
    }

    public Money interestRateCreditCard(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).get();
        Integer transaction = Period.between(creditCard.getLastInterestDay(), LocalDate.now()).getMonths();
        if (transaction >= 1) {
            BigDecimal firstMonth = new BigDecimal(1);
            BigDecimal lastMonth = new BigDecimal(12);
            for (int i = 0; i < transaction; i++) {
                Money interestRate = new Money(creditCard.getBalance().getAmount().multiply(firstMonth.add(creditCard.getInterestRate().divide(lastMonth))));
                creditCard.setBalance(interestRate);
            }
            creditCard.setLastInterestDay(LocalDate.now());
            creditCardRepository.save(creditCard);
        }
        return creditCard.getBalance();
    }
}
