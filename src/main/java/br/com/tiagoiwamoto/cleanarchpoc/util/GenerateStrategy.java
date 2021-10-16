package br.com.tiagoiwamoto.cleanarchpoc.util;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 19:33
 */

import br.com.tiagoiwamoto.cleanarchpoc.core.domain.User;
import br.com.tiagoiwamoto.cleanarchpoc.core.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class GenerateStrategy {

    public Strategy generate(User user) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (Strategy)
                user
                        .getPreference()
                        .getStrategyToUse()
                        .getDeclaredConstructor()
                        .newInstance();
    }

}
