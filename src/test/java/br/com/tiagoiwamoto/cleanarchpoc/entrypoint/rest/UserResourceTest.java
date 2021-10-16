package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest;

import br.com.tiagoiwamoto.cleanarchpoc.core.usecase.UserCreateUsecase;
import br.com.tiagoiwamoto.cleanarchpoc.core.usecase.UserDeleteUsecase;
import br.com.tiagoiwamoto.cleanarchpoc.core.usecase.UserRecoveryUsecase;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiResponseDto;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import br.com.tiagoiwamoto.cleanarchpoc.util.AppMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/10/2021 | 06:51
 */

@ExtendWith(SpringExtension.class)
class UserResourceTest {

    @InjectMocks
    private UserResource userResource;
    @Mock
    private UserCreateUsecase userCreateUsecase;
    @Mock
    private UserRecoveryUsecase userRecoveryUsecase;
    @Mock
    private UserDeleteUsecase userDeleteUsecase;

    @Test
    @DisplayName("Chamada do endpoint /api/v1/users no verbo POST")
    void create() {
        when(this.userCreateUsecase.prepareToCreateOrUpdateUser(any()))
                .thenReturn(ApiResponseDto.of(HttpStatus.CREATED.name(), AppMock.mockUserDto(), AppMessage.API_SUCCESS));
        Assertions.assertEquals(HttpStatus.CREATED, this.userResource.create(AppMock.mockUserDto()).getStatusCode());
    }

    @Test
    @DisplayName("Chamada do endpoint /api/v1/users no verbo PUT")
    void update() {
        when(this.userCreateUsecase.prepareToCreateOrUpdateUser(any()))
                .thenReturn(ApiResponseDto.of(HttpStatus.OK.name(), AppMock.mockUserDto(), AppMessage.API_SUCCESS));
        Assertions.assertEquals(HttpStatus.OK, this.userResource.update(AppMock.mockUserDto()).getStatusCode());

    }

    @Test
    @DisplayName("Chamada do endpoint /api/v1/users no verbo GET")
    void recoverListOfUsers() {
        List<UserDto> mockUsers = new ArrayList<>();
        mockUsers.add(AppMock.mockUserDto());
        when(this.userRecoveryUsecase.prepareToRecoverUsers(any(Pageable.class)))
                .thenReturn(ApiResponseDto.of(HttpStatus.OK.name(), mockUsers, AppMessage.API_SUCCESS));
        Assertions.assertEquals(HttpStatus.OK, this.userResource.recoverListOfUsers(Pageable.ofSize(20)).getStatusCode());
    }

    @Test
    @DisplayName("Chamada do endpoint /api/v1/users/{id} no verbo GET")
    void recoverUser() {
        when(this.userRecoveryUsecase.prepareToRecoverUser(anyLong()))
                .thenReturn(ApiResponseDto.of(HttpStatus.OK.name(), AppMock.mockUserDto(), AppMessage.API_SUCCESS));
        Assertions.assertEquals(HttpStatus.OK, this.userResource.recoverUser(1L).getStatusCode());
    }

    @Test
    @DisplayName("Chamada do endpoint /api/v1/users/{id} no verbo DELETE")
    void deleteUser() {
        when(this.userDeleteUsecase.prepareToDeleteUser(anyLong()))
                .thenReturn(ApiResponseDto.of(HttpStatus.OK.name(), AppMock.mockUserDto(), AppMessage.API_SUCCESS));
        Assertions.assertEquals(HttpStatus.OK, this.userResource.deleteUser(1L).getStatusCode());
    }
}