package com.example.BankingProject.dtos;

import com.example.BankingProject.enums.Status;

public class StatusDTO {

    private Long id;
    private Status status;

    public StatusDTO(Long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
