package com.example.BankingProject.models.users;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
}
