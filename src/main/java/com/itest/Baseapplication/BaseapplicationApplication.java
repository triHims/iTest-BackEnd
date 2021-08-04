package com.itest.Baseapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.itest.Baseapplication")
@EntityScan("com.itest.Baseapplication")
public class BaseapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseapplicationApplication.class, args);
	}

}
