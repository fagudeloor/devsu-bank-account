package com.co.challenge.devsubankaccount.repositories.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Movement generated by hbm2java
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account", catalog = "challenge_devsu_account")
public class Account implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "person_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID personId;

    @Column(name = "account_type", nullable = false, length = 16)
    private String accountType;

    @Column(name = "number_account")
    private Integer numberAccount;

    @Column(name = "account_balance", precision = 22, scale = 0)
    private Double accountBalance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", length = 19)
    private Timestamp createdDate;

    @Column(name = "state", length = 16)
    private String state;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Movement> movements = new HashSet<Movement>(0);

}
