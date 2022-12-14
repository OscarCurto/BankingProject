package com.example.BankingProject.services.users.interfaces;

import com.example.BankingProject.dtos.*;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.users.Admin;
import com.example.BankingProject.models.users.ThirdPartyUser;
import com.example.BankingProject.models.users.User;

import java.math.BigDecimal;
import java.util.List;

public interface AdminServiceInterface {

    List<Account> showAccounts();
    Account createAccount(CreateAccountDTO createAccountDTO);
    ThirdPartyUser createThirdPartyUser(ThirdPartyDTO thirdPartyDTO);
    Admin createAdminUser(AdminDTO adminDTO);
    void deleteAccount(Long id);
    BigDecimal checkBalanceAdmin(Long id);
    Account modifyBalanceAdmin(ModifyBalanceDTO modifyBalanceDTO);
    Account modifyStatus(StatusDTO statusDTO);
    List<User> getAllUsers();
}
