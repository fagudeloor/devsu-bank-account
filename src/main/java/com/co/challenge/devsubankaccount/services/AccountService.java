package com.co.challenge.devsubankaccount.services;

import com.co.challenge.devsubankaccount.web.model.AccountDto;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    Optional<AccountDto> getAccountById(UUID accountId);

    Page<AccountDto> listAccount(Integer pageNumber, Integer pageSize);

    AccountDto createNewAccount(AccountDto accountDto);

    Optional<AccountDto> updateAccountById(UUID accountId, AccountDto accountDto);

    Optional<AccountDto> patchAccountById(UUID accountId, AccountDto accountDto);

    boolean removeAccountById(UUID accountId);
}
