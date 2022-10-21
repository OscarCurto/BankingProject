package com.example.BankingProject.services.users;

import com.example.BankingProject.dtos.*;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.*;
import com.example.BankingProject.models.users.*;
import com.example.BankingProject.repositories.accounts.*;
import com.example.BankingProject.repositories.users.*;
import com.example.BankingProject.services.accounts.CheckingAccountService;
import com.example.BankingProject.services.accounts.CreditCardService;
import com.example.BankingProject.services.accounts.SavingService;
import com.example.BankingProject.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

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

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /*
     * Show accounts
     * Create a new account
     * Create a new ThirdPartyUser
     * Create a new Admin
     * Delete an existing account
     * Check balance (admin)
     * Modify balance
     * Modify status account
     * Get all users
     * */

    //Use this method to show a list of accounts
    public List<Account> showAccounts() {
        return accountRepository.findAll();
    }

    //Use this method to register a new account
    public Account createAccount(CreateAccountDTO createAccountDTO) {
        if (!accountHolderRepository.findById(createAccountDTO.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please select an existing ID.");
        }

        AccountHolder account = accountHolderRepository.findById(createAccountDTO.getId()).get();

        switch (createAccountDTO.getAccountType().trim().toLowerCase()) {
            case "checking":
                return checkingAccountService.createCheckingAccount(createAccountDTO);
            case "saving":
                return savingService.createSavingAccount(createAccountDTO);
            case "creditCard":
                return creditCardService.createCreditCardAccount(createAccountDTO);
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Please choose one between Checking, Savings or CreditCard.");
    }

    //Use this method to create a ThirdPartyUser Account
    public ThirdPartyUser createThirdPartyUser(ThirdPartyDTO thirdPartyDTO) {
        return thirdPartyUserRepository.save(new ThirdPartyUser(thirdPartyDTO.getName(), thirdPartyDTO.getPassword(), thirdPartyDTO.getHashedKey()));
    }

    //Use this method to create an Admin User
    public Admin createAdminUser(AdminDTO adminDTO) {
        Admin admin = new Admin(adminDTO.getName(), passwordEncoder.encode(adminDTO.getPassword()));
        userRepository.save(admin);
        roleRepository.save(new Role("ADMIN", admin));
        return admin;
    }

    //Use this method to delete an account
    public void deleteAccount(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);

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
    public Account modifyBalanceAdmin(ModifyBalanceDTO modifyBalanceDTO) {
        Account account = accountRepository.findById(modifyBalanceDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        account.setBalance(new Money(modifyBalanceDTO.getAmount()));
        accountRepository.save(account);
        return account;
    }

    //Use this method to modify the status of an existing account
    public Account modifyStatus(StatusDTO statusDTO) {
        Account account = accountRepository.findById(statusDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        account.setStatus(statusDTO.getStatus());
        accountRepository.save(account);
        return account;
    }

    //Use this method to get all Users of an existing account
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}