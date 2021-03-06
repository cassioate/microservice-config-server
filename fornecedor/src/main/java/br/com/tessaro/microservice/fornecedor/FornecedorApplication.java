package br.com.tessaro.microservice.fornecedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class FornecedorApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.cloud.bootstrap.enabled","true");
		SpringApplication.run(FornecedorApplication.class, args);
	}

}
