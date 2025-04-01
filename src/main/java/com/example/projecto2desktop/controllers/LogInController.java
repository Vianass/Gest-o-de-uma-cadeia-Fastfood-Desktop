package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.services.FuncionarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
        campoPassword.setOnAction(event -> handleLogin()); // <--- aqui está a magia
    }

    @FXML
    private void handleLogin() {
        String email = campoEmail.getText();
        String password = campoPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            loginMessage.setText("Por favor, preencha todos os campos.");
            return;
        }

        if (funcionarioService.autenticar(email, password)) {
            try {
                // Carregar a tela de menu
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/projecto2desktop/menu.fxml"));
                Parent menuView = loader.load();

                // Criar nova cena
                Scene menuScene = new Scene(menuView);

                // Obter o palco atual
                Stage stage = (Stage) campoEmail.getScene().getWindow();

                // Definir a nova cena
                stage.setScene(menuScene);
                stage.setTitle("Burguo Nervoso - Menu");
                stage.show();
                stage.setMaximized(true);
            } catch (IOException e) {
                e.printStackTrace();
                loginMessage.setText("Erro ao carregar o menu.");
            }
        } else {
            loginMessage.setText("Email ou senha incorretos.");
        }
    }
}