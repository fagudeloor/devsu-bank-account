package com.co.challenge.devsubankaccount.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountDto implements Serializable {

    @FieldNameConstants.Exclude
    static final long serialVersionUID = -2063748920231396988L;

    private UUID id;
    private UUID personId;
    private String accountType;
    private Integer numberAccount;
    private Double accountBalance;
    private String state;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape= JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
}
