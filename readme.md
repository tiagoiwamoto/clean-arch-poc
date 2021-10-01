        br.com.tiagoiwamoto.clean
            - config                -> separar por package com a configuração de cada serviço. Ex: kafka, redis, database, etc...
                - database
                - swagger
                - kafka
            - core                  -> Motor do serviço, toda a regra camada de acesso a base, tratamento de erros
                - dataprovider      -> Camada com acesso a repositorios, integração com outras apis, acesso a arquivos.
                - entity            -> Todas as entidades do sistema
                - error             -> Exceptions personalizadas
                - usecase           -> Onde a regra de negócio é aplicada
                - repository        -> Camada responsável por realizar a conexão com banco de dados.
            - entrypoint            -> Toda forma de acesso a este serviço é incluida nesta camada, rest, batch, consumer, etc...
                - rest
                    - dto           -> Dentro de cada pacote de entrada deve ter seus DTOS para que seja feita a conversão.
                - kafka

Swagger URI
http://localhost:8082/swagger-ui/index.html