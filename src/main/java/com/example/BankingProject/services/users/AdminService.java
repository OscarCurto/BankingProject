package com.example.BankingProject.services.users;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.*;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.models.users.Admin;
import com.example.BankingProject.models.users.ThirdPartyUser;
import com.example.BankingProject.models.users.User;
import com.example.BankingProject.repositories.accounts.*;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.repositories.users.AdminRepository;
import com.example.BankingProject.repositories.users.ThirdPartyUserRepository;
import com.example.BankingProject.repositories.users.UserRepository;
import com.example.BankingProject.services.accounts.CheckingAccountService;
import com.example.BankingProject.services.accounts.CreditCardService;
import com.example.BankingProject.services.accounts.SavingService;
import com.example.BankingProject.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingService savingService;

    @Autowired
    CheckingAccountService checkingAccountService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ThirdPartyUserRepository thirdPartyUserRepository;

    /*
     * Show accounts
     * Create a new account
     * Create a new ThirdPartyUser
     * Create a new Admin
     * Delete an existing account
     * Check balance (admin)
     * Modify balance
     * Modify status account
     * Modify password TODO
     * Get all users
     * */

    //Use this method to show a list of accounts
    public List<Account> showAccounts() {
        return accountRepository.findAll();
    }

    //Use this method to register a new account
    public Account createAccount(AccountDTO accountDTO) {
        if (!accountHolderRepository.findById(accountDTO.getAccountHolderId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please select an existing ID." );
        }

        AccountHolder accountHolder = accountHolderRepository.findById(accountDTO.getAccountHolderId()).get();

        switch (accountDTO.getAccountType().trim().toLowerCase()) {
            case "checking":
                return checkingAccountService.createCheckingAccount(accountDTO);
            case "saving":
                return savingService.createSavingAccount(accountDTO);
            case "creditCard":
                return creditCardService.createCreditCardAccount(accountDTO);
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Please choose one between Checking, Savings or CreditCard." );
    }

    //Use this method to create a ThirdPartyUser Account
    public ThirdPartyUser createThirdPartyUser(ThirdPartyUser thirdPartyUser){
        return thirdPartyUserRepository.save(thirdPartyUser);
    }

    //Use this method to create an Admin User
    public Admin createAdminUser(Admin admin){
        return adminRepository.save(admin);
    }

    //Use this method to delete an account
    public String deleteAccount(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
            return "Account  " + id + " deleted";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account" );
        }
    }

    //Use this method to checkBalance on an account (Admin)
    public BigDecimal checkBalanceAdmin(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            Account account = accountRepository.findById(id).get();
            return account.getBalance().getAmount();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not an existing account" );
        }
    }

    //Use this method to modify the balance of an existing account
    public Account modifyBalanceAdmin(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account" ));
        account.setBalance(new Money(accountDTO.getAmount()));
        accountRepository.save(account);
        return account;
    }

    //Use this method to modify the status of an existing account
    public Account modifyStatus(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account" ));
        account.setStatus(accountDTO.getStatus());
        accountRepository.save(account);
        return account;
    }

    //Use this method to get all Users of an existing account
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}