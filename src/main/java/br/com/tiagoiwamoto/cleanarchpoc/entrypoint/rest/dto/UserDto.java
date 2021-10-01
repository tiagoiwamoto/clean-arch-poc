package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:00
 */

import lombok.Data;

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

}
