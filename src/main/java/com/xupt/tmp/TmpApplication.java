package com.xupt.tmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.xupt.tmp.mapper")
public class TmpApplication {

    public static void main(String[] args) {
         SpringApplication.run(TmpApplication.class, args);
    }

}
