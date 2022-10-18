package com.example.BankingProject.controllers.users.interfaces;

import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderControllerInterface {

    List<Account> showHolderAccounts(Long id);

    AccountHolder createAccountHolderUser(AccountHolder accountHolder);

    BigDecimal checkBalanceUser(CheckOwnBalanceDTO checkOwnBalanceDTO);

    BigDecimal transferMoney(TransferMoneyDTO transferMoneyDTO);
}
