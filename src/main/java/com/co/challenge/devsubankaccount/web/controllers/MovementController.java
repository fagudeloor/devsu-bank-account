package com.co.challenge.devsubankaccount.web.controllers;

import com.co.challenge.devsubankaccount.services.MovementService;
import com.co.challenge.devsubankaccount.web.exceptions.NotFoundException;
import com.co.challenge.devsubankaccount.web.model.MovementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.co.challenge.devsubankaccount.utils.Constants.*;
import static com.co.challenge.devsubankaccount.utils.ErrorMessages.MOVEMENT_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MovementController {

    private final MovementService movementService;

    @GetMapping(MOVEMENT_PATH_ID)
    public MovementDto getMovementById(@PathVariable(MOVEMENT_ID) UUID movementId){
        log.info("Into getMapping id:" + movementId.toString());
        return movementService.getMovementById(movementId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(MOVEMENT_PATH)
    public Page<MovementDto> listMovement(@RequestParam(required = false) Integer pageNumber,
                                          @RequestParam(required = false) Integer pageSize){

        return movementService.listMovement(pageNumber, pageSize);
    }

    @PostMapping(MOVEMENT_PATH)
    public ResponseEntity createNewMovement(@RequestBody MovementDto movementDto){
        log.info("Into postMapping id: " + movementDto.getAccountId());
        MovementDto createdMovement = movementService.createNewMovement(movementDto);

        log.debug("Movement created: " + movementDto.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add(LOCATION, MOVEMENT_PATH + SLASH + createdMovement.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(MOVEMENT_PATH_ID)
    public ResponseEntity updateMovementById(@PathVariable(MOVEMENT_ID) UUID movementId, @RequestBody MovementDto movementDto){
        log.info("Into putMapping id: " + movementId.toString());

        if(movementService.updateMovementById(movementId, movementDto).isEmpty())
            throw new NotFoundException(MOVEMENT_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(MOVEMENT_PATH_ID)
    public ResponseEntity patchMovement(@PathVariable(MOVEMENT_ID) UUID movementId, @RequestBody MovementDto movementDto){
        log.info("Into patchMapping id: " + movementId.toString());

        if(movementService.patchMovementById(movementId, movementDto).isEmpty())
            throw new NotFoundException(MOVEMENT_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(MOVEMENT_PATH_ID)
    public ResponseEntity removeMovement(@PathVariable(MOVEMENT_ID) UUID movementId){
        log.info("Into deleteMapping id: " + movementId.toString());
        if(!movementService.removeMovementById(movementId)){
            throw new NotFoundException(MOVEMENT_NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
