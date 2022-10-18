package com.example.BankingProject.services.users;

import com.example.BankingProject.repositories.users.ThirdPartyUserRepository;
import com.example.BankingProject.services.users.interfaces.ThirdPartyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyUserService implements ThirdPartyUserServiceInterface {

    @Autowired
    ThirdPartyUserRepository thirdPartyUserRepository;
}
