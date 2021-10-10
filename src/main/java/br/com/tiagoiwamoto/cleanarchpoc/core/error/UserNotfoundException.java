package br.com.tiagoiwamoto.cleanarchpoc.core.error;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 08/10/2021 | 06:18
 */

import br.com.tiagoiwamoto.cleanarchpoc.util.AppMessage;

public class UserNotfoundException extends RuntimeException{

    public UserNotfoundException() {
        super(AppMessage.Error.USER_NOT_FOUND);
    }
}
