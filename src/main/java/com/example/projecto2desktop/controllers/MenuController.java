package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuController {

    @FXML
    private void handleFornecedores() {
        try {
            System.out.println("Tentando abrir fornecedores...");
            Projecto2desktopApplication.carregarTelaFornecedores();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarErro("Não foi possível carregar a tela de Fornecedores");
        }
    }

    @FXML
    private void handleEncomendas() {
        try {
            Projecto2desktopApplication.carregarTelaEfetuarEncomenda();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarErro("Não foi possível carregar a tela de Encomendas");
        }
    }

    @FXML
    private void handleArmazens() {
        try {
            Projecto2desktopApplication.carregarTelaArmazens();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarErro("Não foi possível carregar a tela de Armazéns");
        }
    }

    @FXML
    private void handleDistribuicao() {
        try {
            Projecto2desktopApplication.carregarTelaDistribuicao();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarErro("Não foi possível carregar a tela de Distribuição");
        }
    }

    @FXML
    private void handleSair(ActionEvent event) {
        try {
            Projecto2desktopApplication.carregarTelaLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarTela(String tela) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/projecto2desktop/" + tela + ".fxml"));
            Parent view = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela: " + tela);
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
