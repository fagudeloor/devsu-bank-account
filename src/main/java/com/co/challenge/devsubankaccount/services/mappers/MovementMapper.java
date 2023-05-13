package com.co.challenge.devsubankaccount.services.mappers;

import com.co.challenge.devsubankaccount.repositories.domain.Movement;
import com.co.challenge.devsubankaccount.web.model.MovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {DateMapper.class})
public interface MovementMapper {

    @Mapping(target="accountId", source="account.id")
    MovementDto movementToMovementDto(Movement movement);

    @Mapping(target="account.id", source="accountId")
    Movement movementDtoToMovement(MovementDto movementDto);

}
