package com.example.BankingProject.controllers.users;

import com.example.BankingProject.controllers.users.interfaces.UserControllerInterfaces;
import com.example.BankingProject.models.users.User;
import com.example.BankingProject.repositories.users.UserRepository;
import com.example.BankingProject.services.users.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserControllerInterfaces {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<User> findAll(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails.getUsername());

        return userRepository.findAll();
    }
}
