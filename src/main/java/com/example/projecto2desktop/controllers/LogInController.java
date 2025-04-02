package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.models.Funcionario;
import com.example.projecto2desktop.services.FuncionarioService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Optional;

@Controller
public class LogInController {

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoPassword;

    @FXML
    private Label loginMessage;

    @Autowired
    private FuncionarioService funcionarioService;

    @FXML
    public void initialize() {
        campoPassword.setOnAction(event -> {
            handleLogin();
        });
    }

    @FXML
    private void handleLogin() {
        String email = campoEmail.getText();
        String senha = campoPassword.getText();

        Optional<Funcionario> optFuncionario = funcionarioService.autenticar(email, senha);

        if (optFuncionario.isPresent()) {
            Funcionario f = optFuncionario.get();

            System.out.println("LOGIN BEM-SUCEDIDO");
            System.out.println("Email: " + f.getEmail());
            System.out.println("Cargo: '" + f.getCargo() + "'"); // debug

            String cargo = f.getCargo().trim().toLowerCase();

            try {
                if (cargo.contains("gestor")) {
                    Projecto2desktopApplication.carregarTelaMenu(); // Menu de administração
                } else {
                    Projecto2desktopApplication.carregarTelaMenuFuncionario(); // Menu do funcionário
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            loginMessage.setText("Credenciais incorretas.");
            loginMessage.setStyle("-fx-text-fill: red;");
        }
    }
}

