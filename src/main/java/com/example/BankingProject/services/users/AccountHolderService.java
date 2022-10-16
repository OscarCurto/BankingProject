package com.example.BankingProject.services.users;

import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.services.accounts.interfaces.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHolderService implements AccountServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    //Use this method to log into your account

    //Use this method to check balance (User)
    public BigDecimal checkBalance(Long id){
        if (accountRepository.findById(id).isPresent()){
            Account account = accountRepository.findById(id).get();
            return account.getBalance().getAmount();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not an existing account");
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
