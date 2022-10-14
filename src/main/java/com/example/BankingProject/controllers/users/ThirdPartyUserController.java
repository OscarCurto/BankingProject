package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.users.interfaces.ThirdPartyUserControllerInterface;
import com.example.BankingProject.services.users.interfaces.ThirdPartyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyUserController implements ThirdPartyUserControllerInterface {

    @Autowired
    ThirdPartyUserServiceInterface thirdPartyUserServiceInterface;
}
