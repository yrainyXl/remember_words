package com.xl.remember.words;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description: RememberWord
 *
 * @Author: xulei
 * Date: 2025/1/7
 **/
@SpringBootApplication
@EnableScheduling
@MapperScan("com.xl.remember.words.mapper")
public class RememberWord {
  public static void main(String[] args) {
    SpringApplication.run(RememberWord.class, args);
  }
}
