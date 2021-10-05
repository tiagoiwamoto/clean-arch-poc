package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:00
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;
    @NotNull
    @NotBlank
    @Size(min = 4, max = 120)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
    private String address;
    private String neighborhood;
    private String city;
    private String state;

    public static UserDto buildUserDtoFromUser(User user){
        UserDto userDto = build();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    private static UserDto build() {
        return new UserDto();
    }
}
