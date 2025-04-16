package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.models.Fornecedor;
import com.example.projecto2desktop.services.FornecedorService;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class FornecedoresController {

    @Autowired
    private FornecedorService fornecedorService;

    // Componentes da UI
    @FXML private TableView<Fornecedor> tableViewFornecedores;
    @FXML private TableColumn<Fornecedor, Integer> colunaId;
    @FXML private TableColumn<Fornecedor, String> colunaNome;
    @FXML private TableColumn<Fornecedor, String> colunaContacto;
    @FXML private TableColumn<Fornecedor, Double> colunaAvaliacao;
    @FXML private TextField campoNome, campoContacto;
    @FXML private Slider sliderAvaliacao;
    @FXML private Label labelAvaliacao;

    private ObservableList<Fornecedor> listaFornecedores = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColunas();
        carregarFornecedores();
        configurarSlider();
        colunaAvaliacao.setSortType(TableColumn.SortType.DESCENDING);
        tableViewFornecedores.getSortOrder().add(colunaAvaliacao);
        colunaContacto.setCellValueFactory(new PropertyValueFactory<>("contato"));

    }

    @FXML
    private void handleSair(ActionEvent event) {
        try {
            Projecto2desktopApplication.carregarTelaLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void configurarColunas() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaContacto.setCellValueFactory(new PropertyValueFactory<>("contato"));
        colunaAvaliacao.setCellValueFactory(new PropertyValueFactory<>("avaliacao"));
        colunaAvaliacao.setCellFactory(column -> new TableCell<Fornecedor, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.1f ★", item));
                }
            }
        });

    }

    private void carregarFornecedores() {
        listaFornecedores.clear();
        for (Fornecedor f : fornecedorService.listarFornecedores()) {
            double avaliacao = fornecedorService.buscarAvaliacao(f.getId());
            f.setAvaliacao(avaliacao); // <--- aqui atualiza o valor que vai aparecer na tabela
            listaFornecedores.add(f);
        }
        tableViewFornecedores.setItems(listaFornecedores);
    }


    private void configurarSlider() {
        sliderAvaliacao.valueProperty().addListener((obs, oldVal, newVal) -> {
            labelAvaliacao.setText(String.format("%.1f ★", newVal.doubleValue()));
        });
    }

    @FXML
    private void adicionarFornecedor() {
        System.out.println("Botão Adicionar clicado!"); // TESTE

        if (!validarCampos()) return;

        Fornecedor novoFornecedor = new Fornecedor();
        novoFornecedor.setNome(campoNome.getText());
        novoFornecedor.setContato(campoContacto.getText());

        try {
            Fornecedor salvo = fornecedorService.adicionarFornecedor(novoFornecedor);
            fornecedorService.salvarAvaliacao(salvo.getId(), sliderAvaliacao.getValue());

            carregarFornecedores();
            limparCampos();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao adicionar fornecedor: " + e.getMessage());
        }
    }



    @FXML
    private void removerFornecedor() {
        Fornecedor selecionado = tableViewFornecedores.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            fornecedorService.removerFornecedor(selecionado.getId());
            carregarFornecedores();
        } else {
            mostrarAlerta("Erro", "Nenhum fornecedor selecionado!");
        }
    }

    @FXML
    private void atualizarFornecedor() {
        Fornecedor selecionado = tableViewFornecedores.getSelectionModel().getSelectedItem();
        if (selecionado != null && validarCampos()) {
            selecionado.setNome(campoNome.getText());
            selecionado.setContato(campoContacto.getText());

            fornecedorService.adicionarFornecedor(selecionado);
            carregarFornecedores();
        }
    }

    private boolean validarCampos() {
        if (campoNome.getText().isEmpty() || campoContacto.getText().isEmpty()) {
            mostrarAlerta("Validação", "Nome e Contacto são obrigatórios!");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        campoNome.clear();
        campoContacto.clear();
        sliderAvaliacao.setValue(0);
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}