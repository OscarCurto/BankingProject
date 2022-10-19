package com.example.BankingProject.services.users.interfaces;

import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.embedables.Money;

public interface ThirdPartyUserServiceInterface {

    Money transferThirdParty(TransferMoneyDTO transferMoneyDTO, String password);
}
