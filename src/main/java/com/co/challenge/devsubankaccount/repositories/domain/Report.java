package com.co.challenge.devsubankaccount.repositories.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "number_account")
    private Integer numberAccount;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private Double initialBalance;

    @Column(name = "state")
    private String state;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "available_amount")
    private Double availableAmount;
}
