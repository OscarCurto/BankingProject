package com.example.BankingProject.services.users;

import com.example.BankingProject.dtos.CheckOwnBalanceDTO;
import com.example.BankingProject.dtos.TransferMoneyDTO;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.enums.Status;
import com.example.BankingProject.models.accounts.Account;
import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.models.transactions.Transaction;
import com.example.BankingProject.models.users.AccountHolder;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.accounts.CheckingAccountRepository;
import com.example.BankingProject.repositories.accounts.CreditCardRepository;
import com.example.BankingProject.repositories.accounts.SavingRepository;
import com.example.BankingProject.repositories.transactions.TransactionRepository;
import com.example.BankingProject.repositories.users.AccountHolderRepository;
import com.example.BankingProject.services.accounts.CreditCardService;
import com.example.BankingProject.services.accounts.SavingService;
import com.example.BankingProject.services.users.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingService savingService;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    TransactionRepository transactionRepository;

    /*
     * Show all Account Holder accounts
     * Create a new AccountHolderUser
     * Check own balance
     * Transfer Money
     * Transfer Credit Card
     * Transfer Checking
     * Transfer Saving
     * Fraud detecting
     * */

    //Use this method to show all Holder accounts
    public List<Account> showHolderAccounts(Long id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(accountHolder.getPrimaryAccountHolderList());
        accounts.addAll(accountHolder.getSecondaryAccountHolderList());
        return accounts;
    }

    //Use this method to create a new Account Holder User
    public AccountHolder createAccountHolderUser(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    //Use this method to check balance (User)
    public BigDecimal checkBalanceUser(CheckOwnBalanceDTO checkOwnBalanceDTO) {
        AccountHolder accountHolder = accountHolderRepository.findById(checkOwnBalanceDTO.getUserId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing user"));

        Account account = accountRepository.findById(checkOwnBalanceDTO.getAccountId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));

        if (account.getPrimaryAccountHolder() == accountHolder || account.getSecondaryAccountHolder() == accountHolder) {
            if (savingRepository.existsById(checkOwnBalanceDTO.getAccountId())) {
                return savingService.interestRateSaving(checkOwnBalanceDTO.getAccountId()).getAmount();
            }
            if (creditCardRepository.existsById(checkOwnBalanceDTO.getAccountId())) {
                creditCardService.interestRateCreditCard(checkOwnBalanceDTO.getAccountId()).getAmount();
            }
            return account.getBalance().getAmount();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Can't access to this account");
        }
    }

    //Use this method to transfer money between accounts
    public BigDecimal transferMoney(TransferMoneyDTO transferMoneyDTO) {
        Account senderAccount = accountRepository.findById(transferMoneyDTO.getSenderAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        Account receiverAccount = accountRepository.findById(transferMoneyDTO.getSenderAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an existing account"));
        Money sent = new Money(senderAccount.getBalance().decreaseAmount(transferMoneyDTO.getAmount()));
        Money received = new Money(receiverAccount.getBalance().increaseAmount(transferMoneyDTO.getAmount()));
        BigDecimal init = new BigDecimal(0);

        if (senderAccount.getBalance().getAmount().subtract(transferMoneyDTO.getAmount()).compareTo(init) < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not enough money to transfer");
        }

        if (checkingAccountRepository.existsById(transferMoneyDTO.getSenderAccountId())) {
            CheckingAccount checkingSenderAccount = checkingAccountRepository.findById(transferMoneyDTO.getSenderAccountId()).get();
            return this.transferCheckingAccount(checkingSenderAccount, transferMoneyDTO.getAmount(), receiverAccount, sent, received);
        } else if (savingRepository.existsById(transferMoneyDTO.getSenderAccountId())) {
            Saving savinSenderAccount = savingRepository.findById(transferMoneyDTO.getSenderAccountId()).get();
            return this.transferSaving(savinSenderAccount, transferMoneyDTO.getAmount(), receiverAccount, sent, received);
        } else {
            senderAccount.setBalance(sent);
            receiverAccount.setBalance(received);
            Transaction creditCardTransaction = new Transaction("Credit card", senderAccount.getPrimaryAccountHolder().getId(),
                    receiverAccount.getPrimaryAccountHolder().getId(), BigDecimal.valueOf(100), LocalDateTime.now(), senderAccount);
            accountRepository.save(senderAccount);
            accountRepository.save(receiverAccount);
            this.fraudDetection(creditCardTransaction);
            transactionRepository.save(creditCardTransaction);
            return senderAccount.getBalance().getAmount();
        }
    }

    //Use this method to transfer money from a Checking account
    public BigDecimal transferCheckingAccount(CheckingAccount checkingSender, BigDecimal transfer, Account accountReceiver, Money sent, Money received) {
        if (checkingSender.getBalance().getAmount().compareTo(checkingSender.getMinimumBalance().getAmount()) < 0) {
            sent.decreaseAmount(checkingSender.getPenaltyFee().getAmount());
        }

        checkingSender.setBalance(sent);
        accountReceiver.setBalance(received);
        checkingAccountRepository.save(checkingSender);
        Transaction checkingTransaction = new Transaction("Checking", checkingSender.getPrimaryAccountHolder().getId(),
                accountReceiver.getPrimaryAccountHolder().getId(), BigDecimal.valueOf(20), LocalDateTime.now(), checkingSender);
        accountRepository.save(accountReceiver);
        this.fraudDetection(checkingTransaction);
        transactionRepository.save(checkingTransaction);
        return checkingSender.getBalance().getAmount();
    }

    //Use this method to transfer money from a Saving account
    public BigDecimal transferSaving(Saving savingSender, BigDecimal transfer, Account accountReceiver, Money sent, Money received) {
        if (savingSender.getBalance().getAmount().compareTo(savingSender.getMinBalance().getAmount()) < 0) {
            sent.decreaseAmount(savingSender.getPenaltyFee().getAmount());
        }

        savingSender.setBalance(sent);
        accountReceiver.setBalance(received);
        savingRepository.save(savingSender);
        Transaction savingTransaction = new Transaction("Saving", savingSender.getPrimaryAccountHolder().getId(),
                accountReceiver.getPrimaryAccountHolder().getId(), BigDecimal.valueOf(50), LocalDateTime.now(), savingSender);
        accountRepository.save(accountReceiver);
        this.fraudDetection(savingTransaction);
        transactionRepository.save(savingTransaction);
        return savingSender.getBalance().getAmount();
    }

    //Use this method to detect a fraud and freeze the account
    public void fraudDetection(Transaction transaction) {
        Account account = transaction.getAccount();

        List<Transaction> transactions = transactionRepository.findByAccount(account);
        for (Transaction transfer : transactions) {
            if (transfer.getDateTransaction().isAfter(transaction.getDateTransaction().minusSeconds(1))) {
                account.setStatus(Status.ACTIVE);
                accountRepository.save(account);
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Fraud detected, your account is frozen now");
            }
        }
    }
}