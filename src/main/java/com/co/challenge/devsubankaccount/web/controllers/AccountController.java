package com.co.challenge.devsubankaccount.web.controllers;

import com.co.challenge.devsubankaccount.services.AccountService;
import com.co.challenge.devsubankaccount.web.exceptions.NotFoundException;
import com.co.challenge.devsubankaccount.web.model.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.co.challenge.devsubankaccount.utils.Constants.*;
import static com.co.challenge.devsubankaccount.utils.ErrorMessages.ACCOUNT_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping(ACCOUNT_PATH_ID)
    public AccountDto getAccountById(@PathVariable(ACCOUNT_ID) UUID accountId){
        log.info("Into getMapping id:" + accountId.toString());
        return accountService.getAccountById(accountId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(ACCOUNT_PATH)
    public Page<AccountDto> listAccount(@RequestParam(required = false) Integer pageNumber,
                                          @RequestParam(required = false) Integer pageSize){

        return accountService.listAccount(pageNumber, pageSize);
    }

    @PostMapping(ACCOUNT_PATH)
    public ResponseEntity createNewAccount(@RequestBody AccountDto accountDto){
        log.info("Into postMapping id: " + accountDto.getId());
        AccountDto createdAccount = accountService.createNewAccount(accountDto);

        log.debug("Account created: " + accountDto.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add(LOCATION, ACCOUNT_PATH + SLASH + createdAccount.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(ACCOUNT_PATH_ID)
    public ResponseEntity updateAccountById(@PathVariable(ACCOUNT_ID) UUID accountId, @RequestBody AccountDto accountDto){
        log.info("Into putMapping id: " + accountId.toString());

        if(accountService.updateAccountById(accountId, accountDto).isEmpty())
            throw new NotFoundException(ACCOUNT_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(ACCOUNT_PATH_ID)
    public ResponseEntity patchAccount(@PathVariable(ACCOUNT_ID) UUID accountId, @RequestBody AccountDto accountDto){
        log.info("Into patchMapping id: " + accountId.toString());

        if(accountService.patchAccountById(accountId, accountDto).isEmpty())
            throw new NotFoundException(ACCOUNT_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(ACCOUNT_PATH_ID)
    public ResponseEntity removeAccount(@PathVariable(ACCOUNT_ID) UUID accountId){
        log.info("Into deleteMapping id: " + accountId.toString());
        if(!accountService.removeAccountById(accountId)){
            throw new NotFoundException(ACCOUNT_NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
