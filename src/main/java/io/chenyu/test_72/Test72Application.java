package io.chenyu.test_72;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.chenyu.test_72.dao")
public class Test72Application {

    public static void main(String[] args) {
        SpringApplication.run(Test72Application.class, args);
    }

}
