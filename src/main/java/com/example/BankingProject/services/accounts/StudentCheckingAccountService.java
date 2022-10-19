package com.example.BankingProject.services.accounts;

import com.example.BankingProject.repositories.accounts.StudentCheckingAccountRepository;
import com.example.BankingProject.services.accounts.interfaces.StudentCheckingAccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCheckingAccountService implements StudentCheckingAccountServiceInterface {

    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;

}
