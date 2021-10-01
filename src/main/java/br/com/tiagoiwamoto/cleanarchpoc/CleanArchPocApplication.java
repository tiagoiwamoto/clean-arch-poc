package br.com.tiagoiwamoto.cleanarchpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleanArchPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleanArchPocApplication.class, args);
    }

}
