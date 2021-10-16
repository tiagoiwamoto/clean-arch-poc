package br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl;

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.SwapiGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.SwapiMaxIdException;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 19:48
 */

@ExtendWith(SpringExtension.class)
class StarwarStrategyImplTest {

    @InjectMocks
    private StarwarStrategyImpl starwarStrategy;
    @Mock
    private SwapiGateway swapiGateway;

    @Test
    void apply() {
        Mockito.when(this.swapiGateway.call(Mockito.anyInt())).thenReturn(ResponseEntity.ok(Map.of()));
        Assertions.assertEquals(Map.of(), this.starwarStrategy.apply(1));
    }

    @Test
    void applyError() {
        Mockito.when(this.swapiGateway.call(Mockito.anyInt())).thenThrow(FeignException.class);
        Assertions.assertThrows(
                FeignException.class, () -> this.starwarStrategy.apply(1)
        );
    }

    @Test
    void applyIdBiggerThan83() {
        Assertions.assertThrows(
                SwapiMaxIdException.class, () -> this.starwarStrategy.apply(84)
        );
    }

    @Test
    void applyIdLessThan1() {
        Assertions.assertThrows(
                SwapiMaxIdException.class, () -> this.starwarStrategy.apply(0)
        );
    }
}