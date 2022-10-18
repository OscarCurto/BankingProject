package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.users.interfaces.AccountHolderControllerInterface;
import com.example.BankingProject.dtos.AccountHolderDTO;
import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {

    @Autowired
    AccountHolderServiceInterface accountHolderServiceInterface;

    @GetMapping("/holderAccount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<AccountHolder> showHolderAccounts() {
        return accountHolderServiceInterface.showHolderAccounts();
    }

    @PostMapping("holder/createHolderUser")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolderUser(@RequestBody AccountHolder accountHolder){
        return accountHolderServiceInterface.createAccountHolderUser(accountHolder);
    }

    @GetMapping("/checkHolderBalance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal checkBalanceUser(@RequestBody CheckOwnBalanceDTO checkOwnBalanceDTO) {
        return accountHolderServiceInterface.checkBalanceUser(checkOwnBalanceDTO);
    }

    @PatchMapping("/transferHolder")
    @ResponseStatus(HttpStatus.OK)
    public String transferMoney(@RequestBody AccountHolderDTO accountHolderDTO) {
        return accountHolderServiceInterface.transferMoney(accountHolderDTO.getSenderAccountId(),accountHolderDTO.getAmount(), accountHolderDTO.getReceiverAccountId());
    }

}
