package com.example.BankingProject.services.users;

import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.users.ThirdPartyUserRepository;
import com.example.BankingProject.services.users.interfaces.ThirdPartyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ThirdPartyUserService implements ThirdPartyUserServiceInterface {

    @Autowired
    ThirdPartyUserRepository thirdPartyUserRepository;

    @Autowired
    AccountRepository accountRepository;

    public Money transferThirdParty(TransferMoneyDTO transferMoneyDTO, String hashedKey) {
        Account account1 = accountRepository.findById(transferMoneyDTO.getSenderAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        if (!thirdPartyUserRepository.findByHashedKey(hashedKey).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not available");
        }
        Account account2 = accountRepository.findById(transferMoneyDTO.getReceiverAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        if (account1.getBalance().getAmount().compareTo(transferMoneyDTO.getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not enough founds");
        }
        account1.setBalance(new Money(account1.getBalance().decreaseAmount(transferMoneyDTO.getAmount())));
        account2.setBalance(new Money(account2.getBalance().increaseAmount(transferMoneyDTO.getAmount())));
        accountRepository.saveAll(List.of(account1, account2));
        return account1.getBalance();
    }
}
