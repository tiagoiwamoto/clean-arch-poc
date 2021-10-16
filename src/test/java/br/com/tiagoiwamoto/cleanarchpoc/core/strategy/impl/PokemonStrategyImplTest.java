package br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl;

import br.com.tiagoiwamoto.cleanarchpoc.core.dataprovider.gateway.PokeapiGateway;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.PokeapiIdException;
import br.com.tiagoiwamoto.cleanarchpoc.core.error.UserSaveException;
import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 19:38
 */

@ExtendWith(SpringExtension.class)
class PokemonStrategyImplTest {

    @InjectMocks
    private PokemonStrategyImpl pokemonStrategy;
    @Mock
    private PokeapiGateway pokeapiGateway;

    @Test
    void applyOk() {
        Mockito.when(this.pokeapiGateway.call(Mockito.anyInt())).thenReturn(ResponseEntity.ok(Map.of()));
        Assertions.assertEquals(Map.of(), this.pokemonStrategy.apply(1));
    }

    @Test
    void applyError() {
        Mockito.when(this.pokeapiGateway.call(Mockito.anyInt())).thenThrow(FeignException.class);
        Assertions.assertThrows(
                FeignException.class, () -> this.pokemonStrategy.apply(1)
        );
    }

    @Test
    void applyIdLessThanOne() {
        Assertions.assertThrows(
                PokeapiIdException.class, () -> this.pokemonStrategy.apply(0)
        );
    }

    @Test
    void applyIdBiggerThan898() {
        Assertions.assertThrows(
                PokeapiIdException.class, () -> this.pokemonStrategy.apply(899)
        );
    }

    @Test
    void applyIdLessThan10001() {
        Assertions.assertThrows(
                PokeapiIdException.class, () -> this.pokemonStrategy.apply(10000)
        );
    }

    @Test
    void applyIdBiggerThan10220() {
        Assertions.assertThrows(
                PokeapiIdException.class, () -> this.pokemonStrategy.apply(10221)
        );
    }
}