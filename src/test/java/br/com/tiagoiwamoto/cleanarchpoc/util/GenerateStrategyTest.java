package br.com.tiagoiwamoto.cleanarchpoc.util;

import br.com.tiagoiwamoto.cleanarchpoc.mock.AppMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;


/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 19:53
 */

@ExtendWith(SpringExtension.class)
class GenerateStrategyTest {

    @InjectMocks
    private GenerateStrategy generateStrategy;

    @Test
    void generate() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Assertions.assertNotNull(this.generateStrategy.generate(AppMock.mockUser()));
    }
}