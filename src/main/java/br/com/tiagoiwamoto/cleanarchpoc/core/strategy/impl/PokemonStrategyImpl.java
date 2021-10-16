package br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 08:35
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.PokeapiGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.SwapiGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.PokeapiIdException;
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
public class PokemonStrategyImpl implements Strategy {

    @Autowired
    private PokeapiGateway pokeapiGateway;

    @Override
    public Map<String, Object> apply(Integer value) {

        // maximo de 1118 do 1 até 898 e 10001 até 10220
        if(value < 1 || (value > 898 && value < 10001) || value > 10220){
            throw new PokeapiIdException();
        }

        try{
            ResponseEntity<Map<String, Object>> response = this.pokeapiGateway.call(value);
            return response.getBody();
        }catch (Exception e){
            log.error("Erro com a chamada ao serviço pokeapi -> {}", Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

}
