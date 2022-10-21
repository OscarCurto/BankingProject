package com.example.BankingProject.services.accounts;

import com.example.BankingProject.dtos.CreateAccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.models.accounts.StudentCheckingAccount;
import com.example.BankingProject.models.transactions.Transaction;
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
import java.time.LocalDateTime;
import java.time.Period;


@Service
public class CheckingAccountService implements CheckingAccountServiceInterface {

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;


    //Use this method to create a new Checking Account
    public Account createCheckingAccount(CreateAccountDTO createAccountDTO) {

        if (accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
            AccountHolder primaryAccountHolder = accountHolderRepository.findById(createAccountDTO.getId()).get();
            AccountHolder secondaryAccountHolder = null;

            if (createAccountDTO.getSecondaryAccountHolder() != null && accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
                secondaryAccountHolder = accountHolderRepository.findById(createAccountDTO.getId()).get();
            }

            Integer age = Period.between(primaryAccountHolder.getDateOfBirth(), LocalDate.now()).getYears();

            StudentCheckingAccount studentCheckingAccount;
            if (age > 24) {
                CheckingAccount checkingAccount = new CheckingAccount(
                        createAccountDTO.getBalance().getAmount(),
                        primaryAccountHolder,
                        secondaryAccountHolder,
                        createAccountDTO.getPenaltyFee(),
                        createAccountDTO.getCreationDate(),
                        Status.valueOf(createAccountDTO.getStatus())
                );
                return checkingAccountRepository.save(checkingAccount);
            } else {
                studentCheckingAccount = new StudentCheckingAccount(
                        createAccountDTO.getBalance().getAmount(),
                        primaryAccountHolder,
                        secondaryAccountHolder,
                        createAccountDTO.getPenaltyFee(),
                        createAccountDTO.getCreationDate(),
                        Status.valueOf(createAccountDTO.getStatus())
                );
            }
            return studentCheckingAccountRepository.save(studentCheckingAccount);
        }
        throw new IllegalArgumentException("Primary Holder does not exist" );
    }
}
