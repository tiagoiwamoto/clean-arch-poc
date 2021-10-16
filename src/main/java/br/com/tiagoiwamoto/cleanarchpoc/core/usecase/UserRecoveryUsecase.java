package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 07/10/2021 | 20:17
 */

import br.com.tiagoiwamoto.cleanarchpoc.config.rest.ResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.domain.User;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserNotfoundException;
import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.Strategy;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.assembler.UserAssembler;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.cleanarchpoc.util.AppMessage;
import br.com.tiagoiwamoto.cleanarchpoc.util.GenerateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class UserRecoveryUsecase {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;
    private final AutowireCapableBeanFactory autowireCapableBeanFactory;
    private final GenerateStrategy generateStrategy;
    private Strategy strategy;

    public UserRecoveryUsecase(UserRepository userRepository,
                               UserAssembler userAssembler,
                               AutowireCapableBeanFactory autowireCapableBeanFactory,
                               GenerateStrategy generateStrategy) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
        this.autowireCapableBeanFactory = autowireCapableBeanFactory;
        this.generateStrategy = generateStrategy;
    }

    public ResponseDto prepareToRecoverUsers(Pageable pageable){
        Page<User> userPage = this.userRepository.findAll(pageable);
        log.info("users found on data base -> {}", userPage.getTotalElements());

        // Convert to a List<UserDto>
        List<UserDto> listUsersDto = userPage.map(UserDto::buildUserDtoFromUser).getContent();
        // Convert to a EntityModel<UserDto> to add links to each User
        CollectionModel<EntityModel<UserDto>> userDtoCollection = this.userAssembler.toCollectionModel(listUsersDto);
        // Convert to a pageable interface to return correct number of elements and pages
        PageImpl<EntityModel<UserDto>> userDtoPage = new PageImpl<>(
                new ArrayList<>(userDtoCollection.getContent()),
                pageable,
                userPage.getTotalElements()
        );
        userDtoPage.getTotalElements();
        log.info("users converted to userDto -> {}", userDtoPage.getTotalElements());
        return ApiResponseDto.of(
                HttpStatus.OK.name(),
                userDtoPage,
                AppMessage.API_SUCCESS);
    }

    public ResponseDto prepareToRecoverUser(Long id) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Optional<User> optionalUser = this.userRepository.findById(id);
        Map<String, Object> userPreference = new LinkedHashMap<>();
        if(optionalUser.isEmpty()){
            log.error("user with id {} not found on data base", id);
            throw new UserNotfoundException();
        }
        User user = optionalUser.get();
        this.strategy = this.generateStrategy.generate(user);
        this.autowireCapableBeanFactory.autowireBean(this.strategy);
        userPreference = this.strategy.apply(user.getUserPrefenceId());
        UserDto userDto = UserDto.buildUserDtoFromUser(optionalUser.get());
        userDto.setPreference(userPreference);
        userDto = this.userAssembler.toModel(userDto).getContent();
        log.info("user found and converted to userDto -> {}", userDto);
        return ApiResponseDto.of(HttpStatus.OK.name(), this.userAssembler.toModel(userDto), AppMessage.API_SUCCESS);
    }

}
