package com.gadv.desafio_spring;

import com.gadv.desafio_spring.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DesafioSpringApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Main main = new Main();
		main.showMenu(scanner);
		scanner.close();
	}
}
