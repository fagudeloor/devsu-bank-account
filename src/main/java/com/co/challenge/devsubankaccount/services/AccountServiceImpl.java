package com.co.challenge.devsubankaccount.services;

import com.co.challenge.devsubankaccount.repositories.AccountRepository;
import com.co.challenge.devsubankaccount.repositories.domain.Account;
import com.co.challenge.devsubankaccount.services.mappers.AccountMapper;
import com.co.challenge.devsubankaccount.web.model.AccountDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.co.challenge.devsubankaccount.utils.Constants.*;

@Slf4j
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<AccountDto> getAccountById(UUID accountId) {
        return Optional.ofNullable(accountMapper.accountToAccountDto(accountRepository.findById(accountId).orElse(null)));
    }

    @Override
    public Page<AccountDto> listAccount(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Account> all = accountRepository.findAll(pageRequest);

        return all.map(accountMapper::accountToAccountDto);
    }

    @Override
    public AccountDto createNewAccount(AccountDto accountDto) {

        log.debug("creating account: " + accountDto.toString());
        return accountMapper.accountToAccountDto(
                accountRepository.save(accountMapper.accountDtoToAccount(accountDto)));
    }

    @Override
    public Optional<AccountDto> updateAccountById(UUID accountId, AccountDto accountDto) {
        AtomicReference<Optional<AccountDto>> atomicReference = new AtomicReference<>();

        accountRepository.findById(accountId).ifPresentOrElse(foundAccount -> {
            foundAccount.setPersonId(accountDto.getPersonId());
            foundAccount.setAccountType(accountDto.getAccountType());
            foundAccount.setAccountBalance(accountDto.getAccountBalance());
            foundAccount.setNumberAccount(accountDto.getNumberAccount());
            foundAccount.setState(accountDto.getState());
            atomicReference.set(Optional.of(accountMapper.accountToAccountDto(accountRepository.save(foundAccount))));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Optional<AccountDto> patchAccountById(UUID accountId, AccountDto accountDto) {
        AtomicReference<Optional<AccountDto>> atomicReference = new AtomicReference<>();

        accountRepository.findById(accountId).ifPresentOrElse(fAccount -> {
            if (accountDto.getPersonId() != null){
                fAccount.setPersonId(accountDto.getPersonId());
            }
            if (StringUtils.hasText(accountDto.getAccountType())){
                fAccount.setAccountType(accountDto.getAccountType());
            }
            if (accountDto.getAccountBalance() != null){
                fAccount.setAccountBalance(accountDto.getAccountBalance());
            }
            if (accountDto.getNumberAccount() != null){
                fAccount.setNumberAccount(accountDto.getNumberAccount());
            }
            if (StringUtils.hasText(accountDto.getState())){
                fAccount.setState(accountDto.getState());
            }
            atomicReference.set(Optional.of(accountMapper.accountToAccountDto(accountRepository.save(fAccount))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public boolean removeAccountById(UUID accountId) {
        if(accountRepository.existsById(accountId)){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > CERO) {
            queryPageNumber = pageNumber - ONE;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > THOUSAND) {
                queryPageSize = THOUSAND;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc(NUMBER_ACCOUNT));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
