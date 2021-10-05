package br.com.tiagoiwamoto.cleanarchpoc.core.usecase;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 05/10/2021 | 19:43
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.ViacepGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.dto.ViacepDto;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.InvalidCepException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
@Slf4j
public class ViacepUsecase {

    private final ViacepGateway viacepGateway;

    public ViacepDto connectToViacep(String cep){

        ResponseEntity<ViacepDto> viacepResponse = this.viacepGateway.call(cep);
        if(Objects.isNull(Objects.requireNonNull(viacepResponse.getBody()).getCep())){
            throw new InvalidCepException();
        }

        return viacepResponse.getBody();
    }


}
