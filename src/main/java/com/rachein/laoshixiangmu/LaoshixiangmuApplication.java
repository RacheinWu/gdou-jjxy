package com.rachein.laoshixiangmu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rachein.laoshixiangmu.mapper")
public class LaoshixiangmuApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaoshixiangmuApplication.class, args);
    }

}
