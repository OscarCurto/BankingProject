package com.example.BankingProject.services.users;

import com.example.BankingProject.dtos.AccountHolderDTO;
import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.repositories.users.UserRepository;
import com.example.BankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserRepository userRepository;

    /*
     * Show all Account Holder accounts
     * Create a new AccountHolderUser
     * LOGIN TODO
     * Check own balance
     * Transfer Money
     * */

    //Use this method to show all Holder accounts
    public List<AccountHolder> showHolderAccounts() {
        return accountHolderRepository.findAll();
    }

    //Use this method to create a new Account Holder User
    public AccountHolder createAccountHolderUser(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    //Use this method to log into your account

    //Use this method to check balance (User)
    public BigDecimal checkBalanceUser(CheckOwnBalanceDTO checkOwnBalanceDTO) {
        AccountHolder accountHolder = accountHolderRepository.findById(checkOwnBalanceDTO.getUserId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing user"));

        Account account = accountRepository.findById(checkOwnBalanceDTO.getAccountId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));

        if (account.getPrimaryAccountHolder() == accountHolder || account.getSecondaryAccountHolder() == accountHolder) {
            return account.getBalance().getAmount();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access to this account");
        }
    }

    //Use this method to transfer money between accounts
    public String transferMoney(Long senderAccountId, BigDecimal amount, Long receiverAccountId) {
        Account senderAccount = accountRepository.findById(senderAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender id does not exist"));
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver id does not exist"));

        senderAccount.setBalance(new Money(senderAccount.getBalance().decreaseAmount(amount)));
        receiverAccount.setBalance(new Money(receiverAccount.getBalance().increaseAmount(amount)));

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        String transfer = senderAccount.getPrimaryAccountHolder().getName() + "Has sent " + amount + " to " + receiverAccount.getPrimaryAccountHolder().getName();
        return transfer;
    }
}
