package com.example.BankingProject.repositories.users;

import com.example.BankingProject.models.users.ThirdPartyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyUserRepository extends JpaRepository<ThirdPartyUser, Long> {
}
