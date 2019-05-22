package com.itlaoqi.babytun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 */
@SpringBootApplication
@MapperScan("com.itlaoqi.babytun")
@EnableScheduling//自动任务调度
public class BabytunApplication {
	public static void main(String[] args) {
		SpringApplication.run(BabytunApplication.class, args);
	}
}
