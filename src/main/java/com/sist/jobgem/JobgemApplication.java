package com.sist.jobgem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobgemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobgemApplication.class, args);
	}

}
