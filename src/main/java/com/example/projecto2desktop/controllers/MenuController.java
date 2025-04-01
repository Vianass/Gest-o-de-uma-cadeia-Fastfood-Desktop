package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.services.FornecedorService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.io.IOException;

@Controller
public class MenuController {

    @FXML
    private void handleFornecedores() {
        try {
            Projecto2desktopApplication.carregarTelaFornecedores();
        } catch (IOException e) {
            System.err.println("Erro ao carregar fornecedores: " + e.getMessage());
            // Mostrar alerta de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Não foi possível carregar a tela de fornecedores");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleEncomendas() {
        // Lógica para efetuar encomendas
        System.out.println("Abrindo efetuação de encomendas");
        carregarTela("encomendas");
    }

    @FXML
    private void handleProdutos() {
        // Lógica para gerenciar produtos
        System.out.println("Abrindo gestão de produtos");
        carregarTela("produtos");
    }

    @FXML
    private void handleDistribuicao() {
        // Lógica para gerenciar distribuição
        System.out.println("Abrindo gestão de distribuição");
        carregarTela("distribuicao");
    }

    @FXML
    private void handleSair(ActionEvent event) {
        try {
            Projecto2desktopApplication.carregarTelaLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Método auxiliar para carregar diferentes telas
    private void carregarTela(String tela) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/projecto2desktop/" + tela + ".fxml"));
            Parent view = loader.load();

            // Criar nova cena ou substituir conteúdo existente
            // Para exemplo, estamos apenas registrando no console
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela: " + tela);
        }
    }
}
