package br.edu.ifpb.jaas.alunota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AlunotaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlunotaSpringApplication.class, args);
	}

}
