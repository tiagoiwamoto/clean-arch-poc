package br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 07:31
 */

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ApiErrorResponseDto extends ResponseDto implements Serializable {

    private static final long serialVersionUID = -6334049062663871437L;

    private String code;
    private Object message;

    private ApiErrorResponseDto(String code, Object message) {
        super(code, LocalDateTime.now());
        this.code = code;
        this.message = message;
    }

    public static ApiErrorResponseDto of(String code, Object message){
        return new ApiErrorResponseDto(code, message);
    }
}
