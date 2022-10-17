package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.users.interfaces.AdminControllerInterface;
import com.example.BankingProject.dtos.AccountDTO;
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
    public Account createAccount(@RequestBody AccountDTO accountDTO) {
        return adminServiceInterface.createAccount(accountDTO);
    }

    @PostMapping("/admin/createThirdPartyUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdPartyUser createThirdPartyUser(@RequestBody ThirdPartyUser thirdPartyUser){
        return adminServiceInterface.createThirdPartyUser(thirdPartyUser);
    }

    @PostMapping("/admin/createAdminUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createAdminUser(Admin admin){
        return adminServiceInterface.createAdminUser(admin);
    }

    @DeleteMapping("admin/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAccount(@PathVariable Long id) {
        return adminServiceInterface.deleteAccount(id);
    }

    @GetMapping("/checkAdminBalance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BigDecimal checkBalanceAdmin(Long id) {
        return adminServiceInterface.checkBalanceAdmin(id);
    }

    @PatchMapping("/modifyAdminBalance")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyBalanceAdmin(@RequestBody AccountDTO accountDTO) {
        return adminServiceInterface.modifyBalanceAdmin(accountDTO);
    }

    @PatchMapping("/modifyAdminStatus")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyStatus(@RequestBody AccountDTO accountDTO) {
        return adminServiceInterface.modifyStatus(accountDTO);
    }

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> getAllUsers() {
        return adminServiceInterface.getAllUsers();
    }
}
