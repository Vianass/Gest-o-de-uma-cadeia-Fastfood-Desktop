package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.models.Armazem;
import com.example.projecto2desktop.models.ArmazemIngrediente;
import com.example.projecto2desktop.services.ArmazemIngredienteService;
import com.example.projecto2desktop.services.ArmazemService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class ArmazensController {

    @FXML private ComboBox<Armazem> comboArmazem;
    @FXML private TableView<ArmazemIngrediente> tabelaIngredientes;
    @FXML private TableColumn<ArmazemIngrediente, String> colIngrediente;
    @FXML private TableColumn<ArmazemIngrediente, Integer> colQuantidade;

    private final ArmazemService armazemService;
    private final ArmazemIngredienteService armazemIngredienteService;

    public ArmazensController(ArmazemService armazemService, ArmazemIngredienteService armazemIngredienteService) {
        this.armazemService = armazemService;
        this.armazemIngredienteService = armazemIngredienteService;
    }

    @FXML
    public void initialize() {
        comboArmazem.setItems(FXCollections.observableArrayList(armazemService.listarArmazens()));
        comboArmazem.setOnAction(event -> carregarIngredientes());

        colIngrediente.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getIngrediente().getNome()));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    }

    private void carregarIngredientes() {
        Armazem armazemSelecionado = comboArmazem.getValue();
        if (armazemSelecionado != null) {
            List<ArmazemIngrediente> produtos = armazemIngredienteService.listarPorArmazem(armazemSelecionado.getId());
            tabelaIngredientes.setItems(FXCollections.observableArrayList(produtos));
        }
    }

    @FXML
    private void voltarMenu() {
        try {
            com.example.projecto2desktop.Projecto2desktopApplication.carregarTelaMenu();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Não foi possível voltar ao menu.");
            alert.showAndWait();
        }
    }

}
