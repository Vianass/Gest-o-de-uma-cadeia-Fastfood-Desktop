package com.example.projecto2desktop;

import com.example.projecto2desktop.models.Funcionario;
import com.example.projecto2desktop.services.FuncionarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Optional;
import java.util.Scanner;



@SpringBootApplication
public class Projecto2desktopApplication implements CommandLineRunner {

    private final FuncionarioService funcionarioService;

    // Injeção do serviço através de construtor
    public Projecto2desktopApplication(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Projecto2desktopApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login de Funcionário");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Método que retorna Optional<Funcionario>
        Optional<Funcionario> optFuncionario = funcionarioService.autenticar(email, password);

        if (optFuncionario.isPresent()) {
            Funcionario f = optFuncionario.get();
            System.out.println("Login realizado com sucesso!");

            // Verifica o cargo
            if ("Gestor".equalsIgnoreCase(f.getCargo())) {
                System.out.println("Bem-vindo Admin " + f.getNome() + "!");
                // menu admin
            } else {
                System.out.println("Bem-vindo Funcionário " + f.getNome() + "!");
                // menu normal
            }
        } else {
            System.out.println("Credenciais inválidas.");
        }
    }
}