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
import java.util.List;

@Controller
public class DistribuicaoController {

    @FXML private ComboBox<Armazem> comboArmazem;
    @FXML private ComboBox<Ingrediente> comboIngrediente;
    @FXML private ComboBox<Unidade> comboUnidadeDestino;
    @FXML private TextField campoQuantidade;
    @FXML private TableView<DistribuicaoIngrediente> tabelaDistribuicao;
    @FXML private TableColumn<DistribuicaoIngrediente, String> colIngrediente;
    @FXML private TableColumn<DistribuicaoIngrediente, Integer> colQuantidade;
    @FXML private TableColumn<DistribuicaoIngrediente, String> colUnidade;

    private final ArmazemService armazemService;
    private final UnidadeService unidadeService;
    private final IngredienteService ingredienteService;
    private final ArmazemIngredienteService armazemIngredienteService;
    private final DistribuicaoService distribuicaoService;
    private final DistribuicaoIngredienteService distribuicaoIngredienteService;

    private ObservableList<DistribuicaoIngrediente> ingredientesDistribuidos = FXCollections.observableArrayList();

    public DistribuicaoController(ArmazemService armazemService,
                                  UnidadeService unidadeService,
                                  IngredienteService ingredienteService,
                                  ArmazemIngredienteService armazemIngredienteService,
                                  DistribuicaoService distribuicaoService,
                                  DistribuicaoIngredienteService distribuicaoIngredienteService) {
        this.armazemService = armazemService;
        this.unidadeService = unidadeService;
        this.ingredienteService = ingredienteService;
        this.armazemIngredienteService = armazemIngredienteService;
        this.distribuicaoService = distribuicaoService;
        this.distribuicaoIngredienteService = distribuicaoIngredienteService;
    }

    @FXML
    public void initialize() {
        comboArmazem.setItems(FXCollections.observableArrayList(armazemService.listarArmazens()));
        comboArmazem.setOnAction(event -> carregarIngredientesDoArmazem());

        comboUnidadeDestino.setItems(FXCollections.observableArrayList(unidadeService.listarUnidades()));

        colIngrediente.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getIngrediente().getNome()));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colUnidade.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUnidade().getNome()));

        tabelaDistribuicao.setItems(ingredientesDistribuidos);
    }

    private void carregarIngredientesDoArmazem() {
        Armazem armazemSelecionado = comboArmazem.getValue();
        if (armazemSelecionado != null) {
            List<ArmazemIngrediente> ingredientesDisponiveis = armazemIngredienteService.listarPorArmazem(armazemSelecionado.getId());
            List<Ingrediente> ingredientes = ingredientesDisponiveis.stream()
                    .map(ArmazemIngrediente::getIngrediente)
                    .distinct()
                    .toList();
            comboIngrediente.setItems(FXCollections.observableArrayList(ingredientes));
        }
    }

    @FXML
    private void adicionarDistribuicao() {
        Ingrediente ingrediente = comboIngrediente.getValue();
        Unidade unidade = comboUnidadeDestino.getValue();
        int quantidade;

        try {
            quantidade = Integer.parseInt(campoQuantidade.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Quantidade inválida!");
            return;
        }

        if (ingrediente != null && unidade != null && quantidade > 0) {
            DistribuicaoIngrediente di = new DistribuicaoIngrediente();
            di.setIngrediente(ingrediente);
            di.setUnidade(unidade);
            di.setQuantidade(quantidade);
            ingredientesDistribuidos.add(di);

            campoQuantidade.clear();
        } else {
            mostrarAlerta("Erro", "Preencha todos os campos.");
        }
    }

    @FXML
    private void confirmarDistribuicao() {
        if (ingredientesDistribuidos.isEmpty()) {
            mostrarAlerta("Erro", "Adicione pelo menos um ingrediente.");
            return;
        }

        // Criação da nova distribuição
        Distribuicao novaDistribuicao = new Distribuicao();
        novaDistribuicao.setDataEnvio(LocalDate.now());
        novaDistribuicao.setIdArmazem(comboArmazem.getValue());
        novaDistribuicao.setIdUnidade(comboUnidadeDestino.getValue());

        try {
            // Guardar a distribuição
            Distribuicao distribuicaoGuardada = distribuicaoService.salvarDistribuicao(novaDistribuicao);

            // Guardar os ingredientes associados
            for (DistribuicaoIngrediente di : ingredientesDistribuidos) {
                di.setDistribuicao(distribuicaoGuardada);

                // Antes de guardar, verificar se há stock suficiente
                armazemIngredienteService.retirarQuantidade(
                        novaDistribuicao.getIdArmazem().getId(),
                        di.getIngrediente().getId(),
                        di.getQuantidade()
                );

                // Só guarda depois de verificar e retirar stock com sucesso
                distribuicaoIngredienteService.salvar(di);
            }

            ingredientesDistribuidos.clear();
            mostrarAlerta("Sucesso", "Distribuição registada com sucesso!");
        } catch (IllegalArgumentException ex) {
            mostrarAlerta("Erro de Stock", ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Erro", "Ocorreu um erro ao registar a distribuição.");
        }
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
