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

@FeignClient(value = "swapi", url = "${app.gateway.swapi}")
public interface SwapiGateway {

    // Valor maximo é 83
    @GetMapping(path = "/people/{value}/")
    ResponseEntity<Map<String, Object>> call(@PathVariable(name = "value") Integer value);

}
