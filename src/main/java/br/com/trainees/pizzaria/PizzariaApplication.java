package br.com.trainees.pizzaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.trainees.pizzaria.config.EnvLoader;

@SpringBootApplication
public class PizzariaApplication {

	public static void main(String[] args) {
		EnvLoader.loadEnv();
		SpringApplication.run(PizzariaApplication.class, args);
	}

}
