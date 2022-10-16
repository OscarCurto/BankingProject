package com.example.BankingProject.services.accounts;

import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.repositories.accounts.SavingRepository;
import com.example.BankingProject.services.accounts.interfaces.SavingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingService implements SavingServiceInterface {

    @Autowired
    SavingRepository savingRepository;

    //Use this method to show a list of SavingAccounts
    public List<Saving> showSavingAccounts() {
        return savingRepository.findAll();
    }
}
