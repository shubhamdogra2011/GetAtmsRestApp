package com.mobiquity.atms;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobiquity.atms.entities.Atm;


@SpringBootApplication
public class GetAtmsRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetAtmsRestAppApplication.class, args);
	}
	
	@Bean
	public Atm getAtm() {
		return new Atm();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Gson gsonTemplate(GsonBuilder gsonBuilder) {
		return gsonBuilder.setPrettyPrinting().create();
	}
	
//	Required by gson for json to pojo mapping
	@Bean
	public Type getAtmsListType( ) {
		return new TypeToken<ArrayList<Atm>>() {
			}.getType();
	}
}
