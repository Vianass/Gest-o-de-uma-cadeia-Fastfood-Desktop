package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.models.*;
import com.example.projecto2desktop.services.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class EncomendasController {

    @FXML private ComboBox<Fornecedor> comboFornecedor;
    @FXML private ComboBox<Armazem> comboArmazem;
    @FXML private ComboBox<Ingrediente> comboIngrediente;
    @FXML private TextField campoQuantidade;
    @FXML private TableView<EncomendaIngrediente> tabelaIngredientes;
    @FXML private TableColumn<EncomendaIngrediente, String> colIngrediente;
    @FXML private TableColumn<EncomendaIngrediente, Integer> colQuantidade;
    @FXML private TableColumn<EncomendaIngrediente, String> colFornecedor;
    @FXML private TableColumn<EncomendaIngrediente, String> colArmazem;

    private ObservableList<EncomendaIngrediente> listaIngredientes = FXCollections.observableArrayList();

    private final FornecedorService fornecedorService;
    private final ArmazemService armazemService;
    private final IngredienteService ingredienteService;
    private final EncomendaService encomendaService;
    private final EncomendaIngredienteService encomendaIngredienteService;

    public EncomendasController(FornecedorService fornecedorService, ArmazemService armazemService,
                                IngredienteService ingredienteService, EncomendaService encomendaService,
                                EncomendaIngredienteService encomendaIngredienteService) {
        this.fornecedorService = fornecedorService;
        this.armazemService = armazemService;
        this.ingredienteService = ingredienteService;
        this.encomendaService = encomendaService;
        this.encomendaIngredienteService = encomendaIngredienteService;
    }

    @FXML
    public void initialize() {
        comboFornecedor.setItems(FXCollections.observableArrayList(fornecedorService.listarFornecedores()));
        comboArmazem.setItems(FXCollections.observableArrayList(armazemService.listarArmazens()));
        comboIngrediente.setItems(FXCollections.observableArrayList(ingredienteService.listarIngredientes()));

        colIngrediente.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getIngrediente().getNome()));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colFornecedor.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEncomenda().getFornecedor().getNome()));

        colArmazem.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEncomenda().getArmazem().getLocalizacao()));


        tabelaIngredientes.setItems(listaIngredientes);
    }

    @FXML
    private void adicionarIngrediente() {
        Ingrediente ingrediente = comboIngrediente.getValue();
        int quantidade;

        try {
            quantidade = Integer.parseInt(campoQuantidade.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Quantidade inválida!");
            return;
        }

        if (ingrediente != null && quantidade > 0) {
            EncomendaIngrediente ei = new EncomendaIngrediente();
            ei.setIngrediente(ingrediente);
            ei.setQuantidade(quantidade);
            listaIngredientes.add(ei);
            campoQuantidade.clear();
        } else {
            mostrarAlerta("Erro", "Selecione um ingrediente e insira uma quantidade válida.");
        }
    }

    @FXML
    private void confirmarEncomenda() {
        Fornecedor fornecedor = comboFornecedor.getValue();
        Armazem armazem = comboArmazem.getValue();

        if (fornecedor == null || armazem == null || listaIngredientes.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos e adicione pelo menos um ingrediente.");
            return;
        }

        Encomenda encomenda = new Encomenda();
        encomenda.setFornecedor(fornecedor);
        encomenda.setArmazem(armazem);
        encomenda.setDataPedido(LocalDate.now());
        encomenda.setEstado("Pendente");

        Encomenda encomendaSalva = encomendaService.salvarEncomenda(encomenda);

        for (EncomendaIngrediente ei : listaIngredientes) {
            ei.setEncomenda(encomendaSalva);
            encomendaIngredienteService.salvar(ei);
        }

        listaIngredientes.clear();
        mostrarAlerta("Sucesso", "Encomenda registada com sucesso!");
    }

    @FXML
    private void voltarMenu() {
        try {
            Projecto2desktopApplication.carregarTelaMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
