package br.com.tiagoiwamoto.cleanarchpoc.core.domain.enums;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 08:30
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl.PokemonStrategyImpl;
import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.impl.StarwarStrategyImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPreferenceEnum {

    POKEMON("Pokemon", PokemonStrategyImpl.class),
    STARWARS("Starwars", StarwarStrategyImpl.class);

    private String name;
    private Class strategyToUse;

}
