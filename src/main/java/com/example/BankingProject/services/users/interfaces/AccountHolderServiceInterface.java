package com.example.BankingProject.services.users.interfaces;

import com.example.BankingProject.models.users.AccountHolder;

import java.math.BigDecimal;
import java.util.List;

public interface AccountHolderServiceInterface {

    List<AccountHolder> showHolderAccounts();
    BigDecimal checkBalance(Long id);
    String transferMoney(Long senderAccountId, BigDecimal amount, Long receiverAccountId);
}
