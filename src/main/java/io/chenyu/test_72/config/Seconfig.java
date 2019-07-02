package io.chenyu.test_72.config;

import com.github.cage.Cage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class Seconfig {
    @Bean
    public SecureRandom getsecuredom(){
        return new SecureRandom();
    }

    @Bean
    public Cage getcage(){
        return new Cage();
    }
}
