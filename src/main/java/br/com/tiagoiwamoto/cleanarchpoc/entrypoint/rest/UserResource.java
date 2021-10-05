package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:27
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.usecase.UserCreateUsecase;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody @Valid UserDto userDto){
        log.info("starting create() of new user... {}", userDto.toString());
        return new ResponseEntity<>(
                this.userCreateUsecase.prepareToCreateUser(userDto),
                HttpStatus.CREATED);
    }

}
