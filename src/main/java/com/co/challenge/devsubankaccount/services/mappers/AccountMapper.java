package com.co.challenge.devsubankaccount.services.mappers;

import com.co.challenge.devsubankaccount.repositories.domain.Account;
import com.co.challenge.devsubankaccount.web.model.AccountDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);

    Account accountDtoToAccount(AccountDto accountDto);

}
