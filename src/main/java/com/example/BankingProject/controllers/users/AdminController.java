package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.users.interfaces.AdminControllerInterface;
import com.example.BankingProject.dtos.*;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.users.Admin;
import com.example.BankingProject.models.users.ThirdPartyUser;
import com.example.BankingProject.models.users.User;
import com.example.BankingProject.services.users.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AdminController implements AdminControllerInterface {

    @Autowired
    AdminServiceInterface adminServiceInterface;

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Account> showAccounts() {
        return adminServiceInterface.showAccounts();
    }

    @PostMapping("/admin/createAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        return adminServiceInterface.createAccount(createAccountDTO);
    }

    @PostMapping("/admin/createThirdPartyUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdPartyUser createThirdPartyUser(@RequestBody ThirdPartyDTO thirdPartyDTO){
        return adminServiceInterface.createThirdPartyUser(thirdPartyDTO);
    }

    @PostMapping("/admin/createAdminUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdminUser(@RequestBody AdminDTO adminDTO){
        return adminServiceInterface.createAdminUser(adminDTO);
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long id) {
        adminServiceInterface.deleteAccount(id);
    }

    @GetMapping("/checkAdminBalance/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal checkBalanceAdmin(@PathVariable Long id) {
        return adminServiceInterface.checkBalanceAdmin(id);
    }

    @PatchMapping("/modifyAdminBalance")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyBalanceAdmin(@RequestBody ModifyBalanceDTO modifyBalanceDTO) {
        return adminServiceInterface.modifyBalanceAdmin(modifyBalanceDTO);
    }

    @PatchMapping("/modifyAdminStatus")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyStatus(@RequestBody StatusDTO statusDTO) {
        return adminServiceInterface.modifyStatus(statusDTO);
    }

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> getAllUsers() {
        return adminServiceInterface.getAllUsers();
    }
}
