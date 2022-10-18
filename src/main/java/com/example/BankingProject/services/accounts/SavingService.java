package com.example.BankingProject.services.accounts;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.accounts.SavingRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.interfaces.SavingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SavingService implements SavingServiceInterface {

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    //Use this method to show a list of SavingAccounts
    public List<Saving> showSavingAccounts() {
        return savingRepository.findAll();
    }

    //Use this method to create a new Saving Account
    public Saving createSavingAccount(AccountDTO accountDTO){
        if (accountHolderRepository.findById(accountDTO.getPrimaryAccountHolder()).isPresent()){
            AccountHolder primaryAccountHolder = accountHolderRepository.findById(accountDTO.getPrimaryAccountHolder()).get();
            AccountHolder secondaryAccountHolder = null;

            if (accountDTO.getSecondaryAccountHolder() != null && accountHolderRepository.findById(accountDTO.getSecondaryAccountHolder()).isPresent()){
              secondaryAccountHolder = accountHolderRepository.findById(accountDTO.getSecondaryAccountHolder()).get();
            }

            Saving saving = new Saving(
                    accountDTO.getBalance(),
                    primaryAccountHolder,
                    secondaryAccountHolder,
                    accountDTO.getPenaltyFee(),
                    accountDTO.getCreationDate(),
                    accountDTO.getStatus(),
                    accountDTO.getMinBalance(),
                    accountDTO.getInterestRate()

            );
            return savingRepository.save(saving);
        }
        throw new IllegalArgumentException("Primary Holder does not exist" );
    }

    public BigDecimal transferSaving(Saving savingSender, BigDecimal transfer, Account accountReceiver, Money sent, Money received) {
        if (savingSender.getBalance().getAmount().compareTo(savingSender.getMinBalance().getAmount())<0){
            sent.decreaseAmount(savingSender.getPenaltyFee().getAmount());
        }

        savingSender.setBalance(sent);
        accountReceiver.setBalance(received);
        savingRepository.save(savingSender);
        accountRepository.save(accountReceiver);
        return savingSender.getBalance().getAmount();
    }
}
