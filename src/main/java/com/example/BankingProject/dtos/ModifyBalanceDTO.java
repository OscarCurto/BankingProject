package com.example.BankingProject.dtos;

import java.math.BigDecimal;

public class ModifyBalanceDTO {

    private Long id;
    private BigDecimal amount;
    private String accountType;

    public ModifyBalanceDTO(Long id, BigDecimal amount, String accountType) {
        this.id = id;
        this.amount = amount;
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
