package com.formacionspring.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api Clientes", version = "1.0", description = "Crud completo clientes"))
public class ApiRestProyectoFinalApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestProyectoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String pass = "12345";
		for (int i = 0; i < 3; i++) {
			String passBcrypt = passwordEncoder.encode(pass);
			System.out.println(passBcrypt);
		}

	}

}
