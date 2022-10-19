package com.example.BankingProject.services.accounts;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.models.accounts.StudentCheckingAccount;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.accounts.CheckingAccountRepository;
import com.example.BankingProject.repositories.accounts.StudentCheckingAccountRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.interfaces.CheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


@Service
public class CheckingAccountService implements CheckingAccountServiceInterface {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;

    @Autowired
    AccountRepository accountRepository;


    //Use this method to create a new Checking Account
    public Account createCheckingAccount(AccountDTO accountDTO) {

        if (accountHolderRepository.findById(accountDTO.getPrimaryAccountHolder()).isPresent()) {
            AccountHolder primaryAccountHolder = accountHolderRepository.findById(accountDTO.getPrimaryAccountHolder()).get();
            AccountHolder secondaryAccountHolder = null;

            if (accountDTO.getSecondaryAccountHolder() != null && accountHolderRepository.findById(accountDTO.getSecondaryAccountHolder()).isPresent()) {
                secondaryAccountHolder = accountHolderRepository.findById(accountDTO.getSecondaryAccountHolder()).get();
            }

            Integer age = Period.between(primaryAccountHolder.getDateOfBirth(), LocalDate.now()).getYears();

            StudentCheckingAccount studentCheckingAccount;
            if (age > 24) {
                CheckingAccount checkingAccount = new CheckingAccount(
                        accountDTO.getBalance(),
                        primaryAccountHolder,
                        secondaryAccountHolder,
                        accountDTO.getPenaltyFee(),
                        accountDTO.getCreationDate(),
                        accountDTO.getStatus()
                );
                return checkingAccountRepository.save(checkingAccount);
            } else {
                studentCheckingAccount = new StudentCheckingAccount(
                        accountDTO.getBalance(),
                        primaryAccountHolder,
                        secondaryAccountHolder,
                        accountDTO.getPenaltyFee(),
                        accountDTO.getCreationDate(),
                        accountDTO.getStatus()
                );
            }
            return studentCheckingAccountRepository.save(studentCheckingAccount);
        }
        throw new IllegalArgumentException("Primary Holder does not exist" );
    }

    public BigDecimal transferCheckingAccount(CheckingAccount checkingSender, BigDecimal transfer, Account accountReceiver, Money sent, Money received){
        if (checkingSender.getBalance().getAmount().compareTo(checkingSender.getMinimumBalance().getAmount())<0){
            sent.decreaseAmount(checkingSender.getPenaltyFee().getAmount());
        }

        checkingSender.setBalance(sent);
        accountReceiver.setBalance(received);
        checkingAccountRepository.save(checkingSender);
        accountRepository.save(accountReceiver);
        return checkingSender.getBalance().getAmount();
    }
}
