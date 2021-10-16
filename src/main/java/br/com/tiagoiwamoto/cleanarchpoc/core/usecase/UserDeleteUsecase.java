package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 09/10/2021 | 07:14
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.domain.User;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserNotfoundException;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.config.rest.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.cleanarchpoc.util.AppMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class UserDeleteUsecase {

    private final UserRepository userRepository;

    public ResponseDto prepareToDeleteUser(Long userId){
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            log.error("user with id {} not found on data base", userId);
            throw new UserNotfoundException();
        }
        log.info("user found -> {}", optionalUser.get());
        User user = optionalUser.get();
        user.setRemovedAt(LocalDateTime.now());
        log.info("user removed at {}", user.getRemovedAt());
        user = this.userRepository.save(user);
        log.info("user saved at data base.");
        return ApiResponseDto.of(HttpStatus.OK.name(), UserDto.buildUserDtoFromUser(user), AppMessage.API_SUCCESS);
    }

}
