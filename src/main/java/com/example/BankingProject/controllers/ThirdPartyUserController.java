package com.example.BankingProject.controllers;

import com.example.BankingProject.controllers.interfaces.ThirdPartyUserControllerInterface;
import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.services.users.interfaces.ThirdPartyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyUserController implements ThirdPartyUserControllerInterface {

    @Autowired
    ThirdPartyUserServiceInterface thirdPartyUserServiceInterface;

    @PatchMapping("/thirdParty/transfer")
    @ResponseStatus(HttpStatus.OK)
    public Money transferThirdParty(@RequestBody TransferMoneyDTO transferMoneyDTO, @RequestHeader String hashedKey) {
        return thirdPartyUserServiceInterface.transferThirdParty(transferMoneyDTO, hashedKey);
    }
}
