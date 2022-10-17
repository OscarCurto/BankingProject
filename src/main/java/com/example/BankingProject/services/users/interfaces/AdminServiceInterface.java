package com.example.BankingProject.services.users.interfaces;

import com.example.BankingProject.dtos.AccountDTO;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.users.Admin;
import com.example.BankingProject.models.users.ThirdPartyUser;
import com.example.BankingProject.models.users.User;

import java.math.BigDecimal;
import java.util.List;

public interface AdminServiceInterface {

    List<Account> showAccounts();
    Account createAccount(AccountDTO accountDTO);
    ThirdPartyUser createThirdPartyUser(ThirdPartyUser thirdPartyUser);
    Admin createAdminUser(Admin admin);
    String deleteAccount(Long id);
    BigDecimal checkBalanceAdmin(Long id);
    Account modifyBalanceAdmin(AccountDTO accountDTO);
    Account modifyStatus(AccountDTO accountDTO);
    List<User> getAllUsers();
}
