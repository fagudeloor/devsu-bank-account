package com.co.challenge.devsubankaccount.web.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ReportDto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final long serialVersionUID = 4455589168442690635L;

    private Date createdDate;

    private String customerName;

    private Integer numberAccount;

    private String accountType;

    private Double initialBalance;

    private String state;

    private Double amount;

    private Double availableAmount;
}
