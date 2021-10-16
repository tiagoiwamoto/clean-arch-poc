package br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 08:35
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.SwapiGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.SwapiMaxIdException;
import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
@Slf4j
public class StarwarStrategyImpl implements Strategy {

    @Autowired
    private SwapiGateway swapiGateway;

    @Override
    public Map<String, Object> apply(Integer value) {

        if(value > 83 || value < 1){
            throw new SwapiMaxIdException();
        }

        try{
            ResponseEntity<Map<String, Object>> response = this.swapiGateway.call(value);
            return response.getBody();
        }catch (Exception e){
            log.error("Erro com a chamada ao serviço swapi -> {}", Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

}
