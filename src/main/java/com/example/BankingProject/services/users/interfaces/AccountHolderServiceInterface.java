package com.example.BankingProject.services.users.interfaces;

import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.models.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderServiceInterface {

    List<AccountHolder> showHolderAccounts();
    AccountHolder createAccountHolderUser(AccountHolder accountHolder);
    BigDecimal checkBalanceUser(CheckOwnBalanceDTO checkOwnBalanceDTO);
    String transferMoney(Long senderAccountId, BigDecimal amount, Long receiverAccountId);
}
