package com.example.BankingProject.services.users;

import com.example.BankingProject.repositories.users.UserRepository;
import com.example.BankingProject.security.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if (!userRepository.findByName(name).isPresent()) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return new CustomerUserDetails(userRepository.findByName(name).get());
    }
}
