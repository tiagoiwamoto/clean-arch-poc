package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.assembler;

import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 07:35
 */

@ExtendWith(SpringExtension.class)
class UserAssemblerTest {

    @InjectMocks
    private UserAssembler userAssembler;

    @Test
    @DisplayName("Gerando links de uma classe de usuário")
    void addLinks() {
        this.userAssembler.toModel(AppMock.mockUserDto()).getLinks().forEach(line -> {
            if(Objects.requireNonNull(line.getType()).equalsIgnoreCase("GET")){
                Assertions.assertEquals(line.getType(), "GET");
            }else if(Objects.requireNonNull(line.getType()).equalsIgnoreCase("PUT")){
                Assertions.assertEquals(line.getType(), "PUT");
            }else{
                Assertions.assertEquals(line.getType(), "DELETE");
            }
        });
    }
}