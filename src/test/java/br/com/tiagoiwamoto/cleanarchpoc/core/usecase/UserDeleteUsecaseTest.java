package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.repository.UserRepository;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserNotfoundException;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/10/2021 | 21:02
 */

@ExtendWith(SpringExtension.class)
class UserDeleteUsecaseTest {

    @InjectMocks
    private UserDeleteUsecase userDeleteUsecase;
    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Remoção lógica de um usuário com sucesso")
    void prepareToDeleteUserOk() {
        when(this.userRepository.findById(anyLong())).thenReturn(Optional.of(AppMock.mockUser()));
        when(this.userRepository.save(any())).thenReturn(AppMock.mockUser());
        Assertions.assertEquals(HttpStatus.OK.name(), this.userDeleteUsecase.prepareToDeleteUser(1L).getCode());
    }

    @Test
    @DisplayName("Falha ao realizar a remção lógica de um usuário")
    void prepareToDeleteUserUserNotfoundException() {
        when(this.userRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(
                UserNotfoundException.class, () -> this.userDeleteUsecase.prepareToDeleteUser(1L)
        );
    }
}