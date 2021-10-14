package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.ViacepGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.InvalidCepException;
import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 14/10/2021 | 06:35
 */

@ExtendWith(SpringExtension.class)
class ViacepUsecaseTest {

    @InjectMocks
    private ViacepUsecase viacepUsecase;
    @Mock
    private ViacepGateway viacepGateway;

    @Test
    void connectToViacepOk() throws IOException {
        when(this.viacepGateway.call(anyString())).thenReturn(ResponseEntity.ok(AppMock.mockViacepDto()));
        Assertions.assertEquals(AppMock.mockViacepDto().toString(), this.viacepUsecase.connectToViacep("").toString());
    }

    @Test
    void connectToViacepInvalidCepException() {
        when(this.viacepGateway.call(anyString())).thenReturn(ResponseEntity.ok(new ViacepDto()));
        Assertions.assertThrows(
                InvalidCepException.class, () -> this.viacepUsecase.connectToViacep("")
        );
    }
}