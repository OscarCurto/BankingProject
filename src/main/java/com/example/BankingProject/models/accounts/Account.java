package com.example.BankingProject.models.accounts;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

}
