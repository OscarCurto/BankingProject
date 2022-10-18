package com.example.BankingProject.services.users.interfaces;

import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderServiceInterface {

    List<Account> showHolderAccounts(Long id);
    AccountHolder createAccountHolderUser(AccountHolder accountHolder);
    BigDecimal checkBalanceUser(CheckOwnBalanceDTO checkOwnBalanceDTO);
    BigDecimal transferMoney(TransferMoneyDTO transferMoneyDTO);
}
