package br.com.tiagoiwamoto.cleanarchpoc.core.error;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 16/10/2021 | 08:38
 */

import br.com.tiagoiwamoto.cleanarchpoc.util.AppMessage;

public class SwapiMaxIdException extends RuntimeException{

    public SwapiMaxIdException() {
        super(AppMessage.Error.SWAPI_MAX_USER_ID);
    }
}
