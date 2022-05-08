package com.zara.price;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import lombok.Generated;

@SpringBootApplication
@PropertySource(value = { "classpath:application.properties" })
public class PriceApplication {

	@Generated
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PriceApplication.class);
		application.run(args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}

}
