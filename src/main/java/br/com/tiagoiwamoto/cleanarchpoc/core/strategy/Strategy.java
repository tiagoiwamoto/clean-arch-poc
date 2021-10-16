package br.com.tiagoiwamoto.cleanarchpoc.core.strategy;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 08:33
 */

import java.util.Map;

public interface Strategy {

    Map<String, Object> apply(Integer value);

}
