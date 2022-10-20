package com.example.BankingProject.repositories.users;

import com.example.BankingProject.models.users.ThirdPartyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyUserRepository extends JpaRepository<ThirdPartyUser, Long> {

    Optional<String> findByHashedKey(String hashedKey);
}
