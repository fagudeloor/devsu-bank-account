package com.co.challenge.devsubankaccount.repositories;

import com.co.challenge.devsubankaccount.repositories.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MovementRepository extends JpaRepository<Movement, UUID> {

    @Query()
    List<Movement> getDebitTransactionsDayByAccountId(UUID id);
}
