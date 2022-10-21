package com.example.BankingProject;

import com.example.BankingProject.embedables.Address;
import com.example.BankingProject.embedables.Money;
import com.example.BankingProject.models.accounts.CheckingAccount;
import com.example.BankingProject.models.accounts.CreditCard;
import com.example.BankingProject.models.accounts.Saving;
import com.example.BankingProject.models.users.*;
import com.example.BankingProject.repositories.accounts.AccountRepository;
import com.example.BankingProject.repositories.users.RoleRepository;
import com.example.BankingProject.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankingProjectApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        Admin admin = new Admin("Oscar", passwordEncoder.encode("oscar1234"));
        userRepository.save(admin);
        roleRepository.save(new Role("ADMIN",admin));

        AccountHolder accountHolder1 = new AccountHolder();
        accountHolder1.setName("Quim");
        accountHolder1.setPassword(passwordEncoder.encode("quimPassword1"));
        accountHolder1.setDateOfBirth(LocalDate.of(2008, 05, 10));
        accountHolder1.setMail("Quim@gmail.com");
        accountHolder1.setPhone("123456789");
        accountHolder1.setAddress(new Address("Calle falsa123", "Cambrils", "43850", "Tarragona", "Spain"));
        userRepository.save(accountHolder1);
        roleRepository.save(new Role("USER", accountHolder1));

        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Anya");
        accountHolder2.setPassword(passwordEncoder.encode("anyaPassword2"));
        accountHolder2.setDateOfBirth(LocalDate.of(1998, 05, 10));
        accountHolder2.setMail("Anya@gmail.com");
        accountHolder2.setPhone("987654321");
        accountHolder2.setAddress(new Address("Calle falsa321", "Cambrils", "43850", "Tarragona", "Spain"));
        userRepository.save(accountHolder2);
        roleRepository.save(new Role("USER", accountHolder2));

        CheckingAccount checkingAccount1 = new CheckingAccount();
        checkingAccount1.setPrimaryAccountHolder(accountHolder1);
        checkingAccount1.setBalance(new Money(BigDecimal.valueOf(1000)));
        checkingAccount1.setCreationDate(LocalDate.of(2009, 04, 10));
        checkingAccount1.setLastInterestDay(LocalDate.of(2021, 02, 14));
        accountRepository.save(checkingAccount1);

        CheckingAccount checkingAccount2 = new CheckingAccount();
        checkingAccount2.setPrimaryAccountHolder(accountHolder2);
        checkingAccount2.setBalance(new Money(BigDecimal.valueOf(1000)));
        checkingAccount2.setCreationDate(LocalDate.of(2004, 04, 10));
        checkingAccount2.setLastInterestDay(LocalDate.of(2020, 02, 14));
        accountRepository.save(checkingAccount2);

        Saving saving1 = new Saving();
        saving1.setBalance(new Money(BigDecimal.valueOf(1000)));
        saving1.setPrimaryAccountHolder(accountHolder1);
        saving1.setInterestRate(BigDecimal.valueOf(0.5000));
        saving1.setCreationDate(LocalDate.of(2009, 03, 10));
        saving1.setLastInterestDay(LocalDate.of(2022, 05,14));
        accountRepository.save(saving1);

        Saving saving2 = new Saving();
        saving2.setBalance(new Money(BigDecimal.valueOf(1000)));
        saving2.setPrimaryAccountHolder(accountHolder2);
        saving2.setInterestRate(BigDecimal.valueOf(0.5000));
        saving2.setCreationDate(LocalDate.of(2002, 03, 10));
        saving2.setLastInterestDay(LocalDate.of(2019, 05,14));
        accountRepository.save(saving2);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setPrimaryAccountHolder(accountHolder1);
        creditCard1.setCreationDate(LocalDate.of(2009, 05, 14));
        creditCard1.setLastInterestDay(LocalDate.of(2020, 12, 15));
        creditCard1.setCreditLimit(new Money(BigDecimal.valueOf(500L)));
        creditCard1.setInterestRate(BigDecimal.valueOf(0.15));
        creditCard1.setBalance(creditCard1.getCreditLimit());
        accountRepository.save(creditCard1);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setPrimaryAccountHolder(accountHolder2);
        creditCard2.setCreationDate(LocalDate.of(2005, 05, 14));
        creditCard2.setLastInterestDay(LocalDate.of(2020, 12, 15));
        creditCard2.setCreditLimit(new Money(BigDecimal.valueOf(500L)));
        creditCard2.setInterestRate(BigDecimal.valueOf(0.15));
        creditCard2.setBalance(creditCard2.getCreditLimit());
        accountRepository.save(creditCard2);

        ThirdPartyUser thirdPartyUser = new ThirdPartyUser();
        thirdPartyUser.setName("Sergi");
        thirdPartyUser.setPassword((passwordEncoder.encode("sergiPassword")));
        thirdPartyUser.setHashedKey("4321");
        userRepository.save(thirdPartyUser);
    }


    public static void main(String[] args) {
        SpringApplication.run(BankingProjectApplication.class, args);
    }

    //TODO README, UMLS, LIMPIAR

}
