package com.example.BankingProject.services.users;

import com.example.BankingProject.repositories.users.UserRepository;
import com.example.BankingProject.services.users.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;
}
