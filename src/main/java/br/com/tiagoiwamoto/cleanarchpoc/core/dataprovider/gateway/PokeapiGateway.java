package br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 08:19
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(value = "pokeapi", url = "${app.gateway.pokeapi}")
public interface PokeapiGateway {

    // Lista de pokemons
    // maximo de 1118 do 1 até 898 e 10001 até 10220
    @GetMapping(path = "/pokemon/{value}/")
    ResponseEntity<Map<String, Object>> call(@PathVariable(name = "value") Integer value);

}
