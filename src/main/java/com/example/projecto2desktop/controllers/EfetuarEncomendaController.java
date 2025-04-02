package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.models.*;
import com.example.projecto2desktop.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EfetuarEncomendaController {

    @FXML private ComboBox<Fornecedor> comboFornecedor;
    @FXML private ComboBox<Armazem> comboArmazem;
    @FXML private ComboBox<Produto> comboProduto;
    @FXML private TextField campoQuantidade;

    @FXML private TableView<EncomendaProduto> tabelaProdutos;
    @FXML private TableColumn<EncomendaProduto, String> colProduto;
    @FXML private TableColumn<EncomendaProduto, Integer> colQuantidade;

    private ObservableList<EncomendaProduto> listaProdutos = FXCollections.observableArrayList();

    private final FornecedorService fornecedorService;
    private final ArmazemService armazemService;
    private final ProdutoService produtoService;
    private final EncomendaService encomendaService;
    private final EncomendaProdutoService encomendaProdutoService;

    public EfetuarEncomendaController(FornecedorService fornecedorService, ArmazemService armazemService,
                                      ProdutoService produtoService, EncomendaService encomendaService,
                                      EncomendaProdutoService encomendaProdutoService) {
        this.fornecedorService = fornecedorService;
        this.armazemService = armazemService;
        this.produtoService = produtoService;
        this.encomendaService = encomendaService;
        this.encomendaProdutoService = encomendaProdutoService;
    }

    @FXML
    public void initialize() {
        comboFornecedor.setItems(FXCollections.observableArrayList(fornecedorService.listarFornecedores()));
        comboArmazem.setItems(FXCollections.observableArrayList(armazemService.listarArmazens()));
        comboProduto.setItems(FXCollections.observableArrayList(produtoService.listarProdutos()));

        colProduto.setCellValueFactory(data -> data.getValue().getProduto().nomeProperty());
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tabelaProdutos.setItems(listaProdutos);
    }

    @FXML
    private void adicionarProduto() {
        Produto produto = comboProduto.getValue();
        int quantidade;

        try {
            quantidade = Integer.parseInt(campoQuantidade.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Quantidade inválida!");
            return;
        }

        if (produto != null && quantidade > 0) {
            EncomendaProduto ep = new EncomendaProduto();
            ep.setProduto(produto);
            ep.setQuantidade(quantidade);
            listaProdutos.add(ep);
            campoQuantidade.clear();
        } else {
            mostrarAlerta("Erro", "Selecione um produto e insira uma quantidade válida.");
        }
    }

    @FXML
    private void confirmarEncomenda() {
        Fornecedor fornecedor = comboFornecedor.getValue();
        Armazem armazem = comboArmazem.getValue();

        if (fornecedor == null || armazem == null || listaProdutos.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos e adicione pelo menos um produto.");
            return;
        }

        Encomenda encomenda = new Encomenda();
        encomenda.setFornecedor(fornecedor);
        encomenda.setArmazem(armazem);
        encomenda.setDataPedido(LocalDate.now());
        encomenda.setEstado("Pendente");

        Encomenda encomendaSalva = encomendaService.salvarEncomenda(encomenda);

        for (EncomendaProduto ep : listaProdutos) {
            ep.setEncomenda(encomendaSalva);
            encomendaProdutoService.salvar(ep);
        }

        listaProdutos.clear();
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
