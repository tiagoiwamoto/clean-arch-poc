package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 07/10/2021 | 20:17
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.entity.User;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserNotfoundException;
import br.com.tiagoiwamoto.cleanarchpoc.core.util.AppMessage;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class UserRecoveryUsecase {

    private final UserRepository userRepository;

    public ResponseDto prepareToRecoverUsers(Pageable pageable){
        Page<User> userPage = this.userRepository.findAll(pageable);
        log.info("users found on data base -> {}", userPage.getTotalElements());
        //TODO: Converter para UserDto...
        log.info("users converted to userDto ->");
        return ApiResponseDto.of(HttpStatus.OK.name(), userPage, AppMessage.API_SUCCESS);
    }

    public ResponseDto prepareToRecoverUser(Long id){
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isEmpty()){
            log.error("user with id {} not found on data base", id);
            throw new UserNotfoundException();
        }
        UserDto userDto = UserDto.buildUserDtoFromUser(optionalUser.get());
        log.info("user found and converted to userDto -> {}", userDto);
        return ApiResponseDto.of(HttpStatus.OK.name(), userDto, AppMessage.API_SUCCESS);
    }

}
