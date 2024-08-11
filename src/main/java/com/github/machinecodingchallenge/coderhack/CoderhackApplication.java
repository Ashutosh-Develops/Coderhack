package com.github.machinecodingchallenge.coderhack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class CoderhackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderhackApplication.class, args);
	}

}
