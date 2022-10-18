package com.example.BankingProject.dtos;

import java.math.BigDecimal;

public class TransferMoneyDTO {

    private Long senderAccountId;
    private BigDecimal amount;
    private Long receiverAccountId;

    public TransferMoneyDTO(Long senderAccountId, BigDecimal amount, Long receiverAccountId) {
        this.senderAccountId = senderAccountId;
        this.amount = amount;
        this.receiverAccountId = receiverAccountId;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }
}
