package com.example.projecto2desktop;

import com.example.projecto2desktop.services.ClienteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class Projecto2desktopApplication {
    public static void main(String[] args) {
        SpringApplication.run(Projecto2desktopApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ClienteService clienteService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            System.out.println("===== LOGIN DE CLIENTE =====");
            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            boolean sucesso = clienteService.autenticar(email, password);

            if (sucesso) {
                System.out.println("✅ Login realizado com sucesso!");
            } else {
                System.out.println("❌ Email ou password incorretos!");
            }

            scanner.close();
        };
    }
}