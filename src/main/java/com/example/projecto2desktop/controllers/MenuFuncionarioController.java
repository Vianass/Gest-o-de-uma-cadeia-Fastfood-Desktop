package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MenuFuncionarioController {

    @FXML
    private void handleGerirPedidos() {
        try {
            Projecto2desktopApplication.carregarTelaGerirPedidos();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Abrindo gerenciamento de pedidos...");
    }

    @FXML
    private void handleReceberEncomenda() {
        try {
            Projecto2desktopApplication.carregarTelaReceberEncomenda();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Abrindo recebimento de encomendas...");
        // Aqui podes carregar outro FXML se quiseres
    }

    @FXML
    private void handleSair(ActionEvent event) {
        try {
            Projecto2desktopApplication.carregarTelaLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
