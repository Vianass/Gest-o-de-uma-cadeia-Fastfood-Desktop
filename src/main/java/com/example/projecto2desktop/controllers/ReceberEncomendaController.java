package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.models.Encomenda;
import com.example.projecto2desktop.repositories.EncomendaRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class ReceberEncomendaController implements Initializable {

    @FXML
    private TableView<Encomenda> tabelaEncomendas;
    @FXML
    private TableColumn<Encomenda, String> colId;
    @FXML
    private TableColumn<Encomenda, String> colFornecedor;
    @FXML
    private TableColumn<Encomenda, String> colEstado;
    @FXML
    private ComboBox<String> filtroEstadoCombo;

    @Autowired
    private EncomendaRepository encomendaRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId())));
        colFornecedor.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getFornecedor().getNome()));
        colEstado.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getEstado()));

        // Setup do filtro
        filtroEstadoCombo.getItems().addAll("Todas", "Pendente", "Recebida");
        filtroEstadoCombo.setValue("Pendente");

        carregarEncomendas("Pendente");
    }

    private void carregarEncomendas(String filtro) {
        List<Encomenda> lista = encomendaRepository.findAllComFornecedorEArmazem();

        if (!"Todas".equalsIgnoreCase(filtro)) {
            lista = lista.stream()
                    .filter(e -> filtro.equalsIgnoreCase(e.getEstado()))
                    .toList();
        }

        tabelaEncomendas.getItems().setAll(lista);
    }

    @FXML
    private void filtrarEncomendas() {
        String estadoSelecionado = filtroEstadoCombo.getValue();
        carregarEncomendas(estadoSelecionado);
    }

    @FXML
    private void handleMarcarRecebida() {
        Encomenda encomenda = tabelaEncomendas.getSelectionModel().getSelectedItem();
        if (encomenda != null) {
            encomenda.setEstado("Recebida");
            encomendaRepository.save(encomenda);
            carregarEncomendas(filtroEstadoCombo.getValue());
        } else {
            mostrarAlerta("Seleciona uma encomenda para marcar como recebida.");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            Projecto2desktopApplication.carregarTelaMenuFuncionario();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}