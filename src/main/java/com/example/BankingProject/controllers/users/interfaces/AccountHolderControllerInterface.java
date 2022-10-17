package com.example.BankingProject.controllers.users.interfaces;

import com.example.BankingProject.dtos.AccountHolderDTO;
import com.example.BankingProject.models.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderControllerInterface {

    List<AccountHolder> showHolderAccounts();

    AccountHolder createAccountHolderUser(AccountHolder accountHolder);

    BigDecimal checkBalance(Long id);

    String transferMoney(AccountHolderDTO accountHolderDTO);
}
