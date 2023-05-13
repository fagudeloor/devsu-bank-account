package com.co.challenge.devsubankaccount.services;

import com.co.challenge.devsubankaccount.web.model.MovementDto;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface MovementService {

    Optional<MovementDto> getMovementById(UUID movementId);

    Page<MovementDto> listMovement(Integer pageNumber, Integer pageSize);

    MovementDto createNewMovement(MovementDto movementDto);

    Optional<MovementDto> updateMovementById(UUID movementId, MovementDto movementDto);

    Optional<MovementDto> patchMovementById(UUID movementId, MovementDto movementDto);

    boolean removeMovementById(UUID movementId);

}
