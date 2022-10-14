package com.example.BankingProject.services.accounts;

import com.example.BankingProject.repositories.accounts.SavingRepository;
import com.example.BankingProject.services.accounts.interfaces.SavingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingService implements SavingServiceInterface {

    @Autowired
    SavingRepository savingRepository;
}
