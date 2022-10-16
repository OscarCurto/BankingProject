package com.example.BankingProject.controllers.accounts;

import com.example.BankingProject.controllers.accounts.interfaces.SavingControllerInterface;
import com.example.BankingProject.models.accounts.CreditCard;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.services.accounts.interfaces.SavingServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavingController implements SavingControllerInterface {

    @Autowired
    SavingServiceInterface savingServiceInterface;

    @GetMapping("/saving")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Saving> showSavingAccounts() {
        return savingServiceInterface.showSavingAccounts();
    }
}
