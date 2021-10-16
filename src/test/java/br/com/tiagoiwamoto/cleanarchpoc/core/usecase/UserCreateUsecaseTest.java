package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.InvalidCepException;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserSaveException;
import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/10/2021 | 20:49
 */

@ExtendWith(SpringExtension.class)
class UserCreateUsecaseTest {

    @InjectMocks
    private UserCreateUsecase userCreateUsecase;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ViacepUsecase viacepUsecase;

    @Test
    @DisplayName("Criação de um novo usuário com sucesso")
    void prepareToCreateOrUpdateUser() throws IOException {
        when(this.viacepUsecase.connectToViacep(anyString())).thenReturn(AppMock.mockViacepDto());
        when(this.userRepository.save(any())).thenReturn(AppMock.mockUser());
        Assertions.assertEquals(
                HttpStatus.CREATED.name(),
                this.userCreateUsecase.prepareToCreateOrUpdateUser(AppMock.mockUserDto()).getCode()
        );
    }

    @Test
    @DisplayName("Falha ao criar um novo usuário")
    void prepareToCreateOrUpdateUserException() throws IOException {
        when(this.viacepUsecase.connectToViacep(anyString())).thenReturn(AppMock.mockViacepDto());
        when(this.userRepository.save(any())).thenThrow(NullPointerException.class);
        Assertions.assertThrows(
                UserSaveException.class, () -> this.userCreateUsecase.prepareToCreateOrUpdateUser(AppMock.mockUserDto())
        );
    }
}