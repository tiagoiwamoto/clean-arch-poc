package br.com.tiagoiwamoto.cleanarchpoc.core.error;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 30/09/2021 | 21:13
 */

import br.com.tiagoiwamoto.cleanarchpoc.entrypoint.rest.dto.ApiErrorResponseDto;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Realiza o tratamento generico de todos os campos que estivem marcados com o @Valid e tiverem errors
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDto> handlerBindingResult(MethodArgumentNotValidException ex, WebRequest request){
        log.error("Payload com erro ou mal formatado");
        List<ApiErrorResponseDto> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(ApiErrorResponseDto.of(error.getField(), error.getDefaultMessage())));
        return ResponseEntity.badRequest().body(ApiErrorResponseDto.of("4", errors));
    }

    /**
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<ApiErrorResponseDto> handlerBindingResult(InvalidCepException ex, WebRequest request){
        log.error("Payload com erro ou mal formatado");

        return ResponseEntity.badRequest().body(ApiErrorResponseDto.of("4", ex.getMessage()));
    }

    /**
     * Realiza o tratamento para chamadas realizadas pelo feign
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiErrorResponseDto> handlerFeignException(FeignException ex, WebRequest request){
        HttpStatus httpStatus = Optional.of(ex.status()).filter(status -> status > 0).map(HttpStatus::valueOf)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        if(httpStatus == HttpStatus.INTERNAL_SERVER_ERROR){
            return ResponseEntity.status(httpStatus)
                    .body(ApiErrorResponseDto.of("5", "Falha de comunicação com sites parceiros, tente novamente mais tarde"));
        }else{
            return ResponseEntity.status(httpStatus).body(ApiErrorResponseDto.of("3", httpStatus.getReasonPhrase()));
        }
    }

}
