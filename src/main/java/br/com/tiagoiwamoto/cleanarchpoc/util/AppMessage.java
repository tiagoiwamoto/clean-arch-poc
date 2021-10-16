package br.com.tiagoiwamoto.cleanarchpoc.util;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 07/10/2021 | 20:22
 */

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppMessage {

    public static final String API_SUCCESS = "Operação realizada com sucesso";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Error{
        public static final String USER_NOT_FOUND = "Não localizamos este usuário em nossa base de dados";
        public static final String SWAPI_MAX_USER_ID = "O número máximo que pode ser passado é 83 e o minimo é 1";
        public static final String POKEAPI_ID = "O id deve estar entre 1 e 898 ou 10001 e 10220";
        public static final String CREATE_STRATEGY_INSTANCE = "Falha ao criar a instancia do objeto";
    }

}
