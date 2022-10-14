package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.users.interfaces.UserControllerInterfaces;
import com.example.BankingProject.services.users.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserControllerInterfaces {

    @Autowired
    UserServiceInterface userServiceInterface;
}
