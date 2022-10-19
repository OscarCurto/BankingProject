package com.example.BankingProject.services.accounts;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.dtos.CreateAccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.accounts.SavingRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.interfaces.SavingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class SavingService implements SavingServiceInterface {

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    //Use this method to create a new Saving Account
    public Saving createSavingAccount(CreateAccountDTO createAccountDTO) {
        if (accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
            AccountHolder primaryAccountHolder = accountHolderRepository.findById(createAccountDTO.getId()).get();
            AccountHolder secondaryAccountHolder = null;

            if (createAccountDTO.getSecondaryAccountHolder() != null && accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
                secondaryAccountHolder = accountHolderRepository.findById(createAccountDTO.getId()).get();
            }

            Saving saving = new Saving(
                    createAccountDTO.getBalance(),
                    primaryAccountHolder,
                    secondaryAccountHolder,
                    createAccountDTO.getPenaltyFee(),
                    createAccountDTO.getCreationDate(),
                    createAccountDTO.getStatus(),
                    createAccountDTO.getMinBalance(),
                    createAccountDTO.getInterestRate()

            );
            return savingRepository.save(saving);
        }
        throw new IllegalArgumentException("Primary Holder does not exist");
    }

    public BigDecimal transferSaving(Saving savingSender, BigDecimal transfer, Account accountReceiver, Money sent, Money received) {
        if (savingSender.getBalance().getAmount().compareTo(savingSender.getMinBalance().getAmount()) < 0) {
            sent.decreaseAmount(savingSender.getPenaltyFee().getAmount());
        }

        savingSender.setBalance(sent);
        accountReceiver.setBalance(received);
        savingRepository.save(savingSender);
        accountRepository.save(accountReceiver);
        return savingSender.getBalance().getAmount();
    }

    public Money interestRateSaving(Long id) {
        Saving saving = savingRepository.findById(id).get();
        Integer transaction = Period.between(saving.getLastInterestDay(), LocalDate.now()).getYears();
        if (transaction >= 1) {
            BigDecimal year = new BigDecimal(1);
            for (int i = 0; i < transaction; i++) {
                Money interestRate = new Money(saving.getBalance().getAmount().multiply(year.add(saving.getInterestRate())));
                saving.setBalance(interestRate);
                savingRepository.save(saving);
            }
        }
        return saving.getBalance();
    }
}
