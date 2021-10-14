package br.com.tiagoiwamoto.cleanarchpoc.mock;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/10/2021 | 20:50
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.cleanarchpoc.core.domain.User;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class AppMock {

    private AppMock() {
    }

    public static User mockUser(){
        var user = User.build();
        user.setRemovedAt(null);
        user.setCreatedAt(LocalDateTime.now());
        user.setAddress("Rua X");
        user.setCep("01001000");
        user.setCity("City");
        user.setName("Bruce Wayne");
        user.setNeighborhood("Gotham");
        user.setState("Gotham");
        user.setId(1L);
        return user;
    }

    public static UserDto mockUserDto(){
        return UserDto.buildUserDtoFromUser(mockUser());
    }

    public static ViacepDto mockViacepDto() throws IOException {
        String jsonString = Files
                .readString(Path.of("src/test/java/br/com/tiagoiwamoto/cleanarchpoc/mock/viacep_mock.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        ViacepDto viacepDto = objectMapper.readValue(jsonString, ViacepDto.class);
        return viacepDto;
    }
}
