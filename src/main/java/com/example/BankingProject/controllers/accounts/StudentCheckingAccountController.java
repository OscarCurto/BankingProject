package com.example.BankingProject.controllers.accounts;

import com.example.BankingProject.controllers.accounts.interfaces.StudentCheckingAccountControllerInterface;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.models.accounts.StudentCheckingAccount;
import com.example.BankingProject.services.accounts.interfaces.StudentCheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCheckingAccountController implements StudentCheckingAccountControllerInterface {

    @Autowired
    StudentCheckingAccountServiceInterface studentCheckingAccountServiceInterface;

    @GetMapping("/studentChecking")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<StudentCheckingAccount> showStudentCheckingAccounts() {
        return studentCheckingAccountServiceInterface.showStudentCheckingAccounts();
    }
}
