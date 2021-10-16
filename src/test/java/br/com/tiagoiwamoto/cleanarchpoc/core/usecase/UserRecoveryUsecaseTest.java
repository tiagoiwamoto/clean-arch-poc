package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.domain.User;
import br.com.tiagoiwamoto.cleanarchpoc.core.domain.enums.UserPreferenceEnum;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserNotfoundException;
import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.Strategy;
import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl.StarwarStrategyImpl;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.assembler.UserAssembler;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import br.com.tiagoiwamoto.cleanarchpoc.util.GenerateStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyCollection;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/10/2021 | 05:45
 */

@ExtendWith(SpringExtension.class)
class UserRecoveryUsecaseTest {

    @InjectMocks
    private UserRecoveryUsecase userRecoveryUsecase;
    @Mock
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserAssembler userAssembler;
    @Mock
    private Strategy strategy;
    @Mock
    private StarwarStrategyImpl starwarStrategy;
    @Mock
    private GenerateStrategy generateStrategy;

    @Test
    @DisplayName("Recuperando uma lista de usuários com sucesso")
    void prepareToRecoverUsers() {
        List<User> content = new ArrayList<>();
        content.add(AppMock.mockUser());
        content.add(AppMock.mockUser());
        content.add(AppMock.mockUser());
        Page<User> page = new PageImpl<>(content);
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(AppMock.mockUserDto());

        when(this.userRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(this.userAssembler.toCollectionModel(anyCollection())).thenReturn(CollectionModel.wrap(userDtos));

        Pageable pageable = PageRequest.of(0, 20, Sort.by("name"));
        Assertions.assertEquals(
                HttpStatus.OK.name(),
                this.userRecoveryUsecase.prepareToRecoverUsers(pageable).getCode()
        );
    }

    @Test
    @DisplayName("Recuperando um usuário com sucesso")
    void prepareToRecoverUserOk() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        doNothing().when(this.autowireCapableBeanFactory).autowireBean(any());
        when(this.generateStrategy.generate(any())).thenReturn(starwarStrategy);
        when(this.userRepository.findById(anyLong())).thenReturn(Optional.of(AppMock.mockUser()));
        when(this.userAssembler.toModel(any())).thenReturn(EntityModel.of(AppMock.mockUserDto()));
        Assertions.assertEquals(HttpStatus.OK.name(), this.userRecoveryUsecase.prepareToRecoverUser(1L).getCode());
    }

    @Test
    @DisplayName("Nenhum usuário localizado com o id informado")
    void prepareToRecoverUserNotfoundException() {
        when(this.userRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(
                UserNotfoundException.class, () -> this.userRecoveryUsecase.prepareToRecoverUser(1L)
        );
    }
}