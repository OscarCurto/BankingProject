package com.example.BankingProject.services.users;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.accounts.*;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.models.users.User;
import com.example.BankingProject.repositories.accounts.*;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.repositories.users.AdminRepository;
import com.example.BankingProject.repositories.users.UserRepository;
import com.example.BankingProject.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    /*
     * Show accounts
     * Register a new account
     * Delete an existing account
     * Check balance (admin)
     * Modify balance
     * Change status account
     * Get all users
     * Create admin user TODO
     * */

    //Use this method to show a list of accounts
    public List<Account> showAccounts() {
        return accountRepository.findAll();
    }

    //Use this method to register a new account
    public Account createAccount(AccountDTO accountDTO) {
        if (!accountHolderRepository.findById(accountDTO.getAccountHolderId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please select an existing ID.");
        }

        AccountHolder accountHolder = accountHolderRepository.findById(accountDTO.getAccountHolderId()).get();

        if ((LocalDate.now().getYear() - accountHolder.getDateOfBirth().getYear() < 18)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User must be at lease 18 years old.");
        }

        switch (accountDTO.getAccountType().trim().toLowerCase()) {
            case "checking":
                LocalDate dateOfBirth = accountHolder.getDateOfBirth();
                if (LocalDate.now().getYear() - dateOfBirth.getYear() <= 24) {
                    StudentCheckingAccount studentCheckingAccount = new StudentCheckingAccount(accountDTO.getBalance(), accountHolder);
                    studentCheckingAccountRepository.save(studentCheckingAccount);
                    return studentCheckingAccount;
                } else {
                    CheckingAccount checkingAccount = new CheckingAccount(accountDTO.getBalance(), accountHolder);
                    checkingAccountRepository.save(checkingAccount);
                    return checkingAccount;
                }
            case "creditCard":
                CreditCard creditCard = new CreditCard(accountDTO.getBalance(), accountHolder);
                creditCardRepository.save(creditCard);
                return creditCard;
            case "saving":
                Saving saving = new Saving(accountDTO.getBalance(), accountHolder);
                savingRepository.save(saving);
                return saving;
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Please choose one between Checking, Savings or CreditCard.");

    }

    //Use this method to delete an account
    public String deleteAccount(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
            return "Account  " + id + " deleted";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account");
        }
    }

    //Use this method to checkBalance on an account (Admin)
    public BigDecimal checkBalanceAdmin(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            Account account = accountRepository.findById(id).get();
            return account.getBalance().getAmount();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not an existing account");
        }
    }

    //Use this method to modify the balance of an existing account
    public Account modifyBalanceAdmin(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        account.setBalance(new Money(accountDTO.getAmount()));
        accountRepository.save(account);
        return account;
    }

    //Use this method to modify the status of an existing account
    public Account modifyStatus(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        account.setStatus(accountDTO.getStatus());
        accountRepository.save(account);
        return account;
    }

    //Use this method to get all Users of an existing account
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Use this method to create an admin User
}