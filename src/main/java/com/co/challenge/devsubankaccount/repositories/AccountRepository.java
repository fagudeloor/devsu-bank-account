package com.co.challenge.devsubankaccount.repositories;

import com.co.challenge.devsubankaccount.repositories.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
