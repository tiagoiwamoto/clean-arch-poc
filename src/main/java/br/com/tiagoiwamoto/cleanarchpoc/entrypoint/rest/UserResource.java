package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:27
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.usecase.UserCreateUsecase;
import br.com.tiagoiwamoto.cleanarchpoc.core.usecase.UserRecoveryUsecase;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserResource {

    private final UserCreateUsecase userCreateUsecase;
    private final UserRecoveryUsecase userRecoveryUsecase;

    @ApiOperation(value = "Grava um novo usuário em nossa base de dados")
    @ApiResponses(value = {})
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid UserDto userDto){
        log.info("starting create() of new user... {}", userDto.toString());
        return new ResponseEntity<>(
                this.userCreateUsecase.prepareToCreateOrUpdateUser(userDto),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "Grava um novo usuário em nossa base de dados")
    @ApiResponses(value = {})
    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody @Valid UserDto userDto){
        log.info("starting update() a existing user... {}", userDto.toString());
        return new ResponseEntity<>(
                this.userCreateUsecase.prepareToCreateOrUpdateUser(userDto),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera uma lista de usuários paginados pelo número máximo de 100 registros.")
    @ApiResponses(value = {})
    @GetMapping
    public ResponseEntity<ResponseDto> recoverListOfUsers(Pageable pageable){
        log.info("starting recoverListOfUsers()... {}", pageable);
        return new ResponseEntity<>(this.userRecoveryUsecase.prepareToRecoverUsers(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera um usuário pelo seu número identificador único")
    @ApiResponses(value = {})
    @GetMapping(path = "/{userId}")
    public ResponseEntity<ResponseDto> recoverUser(@PathVariable(name = "userId") Long userId){
        log.info("starting recoverUser() with id {}...", userId);
        return new ResponseEntity<>(this.userRecoveryUsecase.prepareToRecoverUser(userId), HttpStatus.OK);
    }

}
