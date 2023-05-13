package com.co.challenge.devsubankaccount.web.model;

import com.co.challenge.devsubankaccount.repositories.domain.Account;
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
public class MovementDto implements Serializable {

    @FieldNameConstants.Exclude
    static final long serialVersionUID = 7579622866585596004L;

    private UUID id;
    private UUID accountId;
    private String movementType;
    private Double amount;
    private Double balance;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape= JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;


}
