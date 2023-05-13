package com.co.challenge.devsubankaccount.services;

import com.co.challenge.devsubankaccount.repositories.AccountRepository;
import com.co.challenge.devsubankaccount.repositories.MovementRepository;
import com.co.challenge.devsubankaccount.repositories.domain.Account;
import com.co.challenge.devsubankaccount.repositories.domain.Movement;
import com.co.challenge.devsubankaccount.services.mappers.MovementMapper;
import com.co.challenge.devsubankaccount.web.exceptions.TransactionNotSupportedException;
import com.co.challenge.devsubankaccount.web.model.MovementDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.co.challenge.devsubankaccount.utils.Constants.*;

@Slf4j
@AllArgsConstructor
@Service
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;

    @Override
    public Optional<MovementDto> getMovementById(UUID movementId) {
        Optional<Movement> movementFound = movementRepository.findById(movementId);

        return Optional.ofNullable(movementMapper.movementToMovementDto(movementFound.get()));
    }

    @Override
    public Page<MovementDto> listMovement(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Movement> all = movementRepository.findAll(pageRequest);

        return all.map(movementMapper::movementToMovementDto);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public MovementDto createNewMovement(MovementDto movementDto) {
        log.debug("creating movement: " + movementDto.toString());
        Movement movement = movementMapper.movementDtoToMovement(movementDto);
        Movement movementProcessed = processTransaction(movement);
        log.debug("creating movement: " + movement.toString());
        return movementMapper.movementToMovementDto(
                movementRepository.save(movement));
    }


    private Movement processTransaction(Movement movement) {
        Optional<Account> account = accountRepository.findById(movement.getAccount().getId());

        updateBalance(movement, account);

        if(movement.getMovementType().equalsIgnoreCase(DEBIT)){
            validateMovement(movement, account);
        }

        accountRepository.save(account.get());
        return movement;
    }

    private void validateMovement(Movement movement, Optional<Account> account) {
        if (account.get().getAccountBalance()<CERO){
            throw new TransactionNotSupportedException("Balance not available");
        }

        if((getDayOnDay(account) + movement.getAmount()) >THOUSAND){
            throw new TransactionNotSupportedException("Daily limit exceeded");
        }
    }

    private Double getDayOnDay(Optional<Account> account) {
        List<Movement> accountByDay = movementRepository.getDebitTransactionsDayByAccountId(account.get().getId());
        log.debug("The balance starting the day was: " + accountByDay.get(0).getBalance());
        double sum = accountByDay.stream().mapToDouble(Movement::getAmount).sum();
        log.debug("Value total debit: " + sum);
        return sum;
    }

    private void updateBalance(Movement movement, Optional<Account> account) {
        movement.setBalance(account.get().getAccountBalance());

        if(movement.getMovementType().equalsIgnoreCase(CREDIT)){
            account.get().setAccountBalance(movement.getBalance() + movement.getAmount());
        } else if(movement.getMovementType().equalsIgnoreCase(DEBIT)){
            account.get().setAccountBalance(movement.getBalance() - movement.getAmount());
        }else {
            throw new TransactionNotSupportedException("The transaction type is not supported: " + movement.getMovementType());
        }
    }

    @Override
    public Optional<MovementDto> updateMovementById(UUID movementId, MovementDto movementDto) {
        AtomicReference<Optional<MovementDto>> atomicReference = new AtomicReference<>();

        movementRepository.findById(movementId).ifPresentOrElse(foundMovement -> {
            foundMovement.setMovementType(movementDto.getMovementType());
            foundMovement.getAccount().setId(movementDto.getAccountId());
            foundMovement.setBalance(movementDto.getBalance());
            foundMovement.setAmount(movementDto.getAmount());
            atomicReference.set(Optional.of(movementMapper.movementToMovementDto(movementRepository.save(foundMovement))));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Optional<MovementDto> patchMovementById(UUID movementId, MovementDto movementDto) {
        AtomicReference<Optional<MovementDto>> atomicReference = new AtomicReference<>();

        movementRepository.findById(movementId).ifPresentOrElse(fMovement -> {
            if (StringUtils.hasText(movementDto.getMovementType())){
                fMovement.setMovementType(movementDto.getMovementType());
            }
            if (movementDto.getAccountId() != null){
                fMovement.getAccount().setId(movementDto.getAccountId());
            }
            if (movementDto.getBalance() != null){
                fMovement.setBalance(movementDto.getBalance());
            }
            if (movementDto.getAmount() != null){
                fMovement.setAmount(movementDto.getAmount());
            }
            atomicReference.set(Optional.of(movementMapper.movementToMovementDto(movementRepository.save(fMovement))));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public boolean removeMovementById(UUID movementId) {
        if(movementRepository.existsById(movementId)){
            movementRepository.deleteById(movementId);
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

        Sort sort = Sort.by(Sort.Order.asc(ID));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
