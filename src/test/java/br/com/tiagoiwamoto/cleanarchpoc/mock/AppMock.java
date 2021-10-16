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
import br.com.tiagoiwamoto.cleanarchpoc.core.domain.enums.UserPreferenceEnum;
import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

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
        user.setPreference(UserPreferenceEnum.STARWARS);
        user.setUserPrefenceId(1);
        return user;
    }

    public static UserDto mockUserDto(){
        UserDto userDto = UserDto.buildUserDtoFromUser(mockUser());
        userDto.setPreferenceEnum(UserPreferenceEnum.STARWARS);
        userDto.setPreferenceId(1);
        userDto.setPreference(new LinkedHashMap<>());
        return userDto;
    }

    public static ViacepDto mockViacepDto() throws IOException {
        String jsonString = Files
                .readString(Path.of("src/test/java/br/com/tiagoiwamoto/cleanarchpoc/mock/viacep_mock.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        ViacepDto viacepDto = objectMapper.readValue(jsonString, ViacepDto.class);
        return viacepDto;
    }
}
