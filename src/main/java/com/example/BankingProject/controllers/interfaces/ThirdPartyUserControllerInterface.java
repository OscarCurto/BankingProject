package com.example.BankingProject.controllers.interfaces;

import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.embedables.Money;

public interface ThirdPartyUserControllerInterface {

    Money transferThirdParty(TransferMoneyDTO transferMoneyDTO, String hashedKey);
}
