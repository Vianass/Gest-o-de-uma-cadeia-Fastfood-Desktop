package com.example.projecto2desktop.controllers;

import com.example.projecto2desktop.Projecto2desktopApplication;
import com.example.projecto2desktop.models.Pedido;
import com.example.projecto2desktop.repositories.PedidoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class GerirPedidosController implements Initializable {

    @FXML
    private TableView<Pedido> tabelaPedidos;
    @FXML
    private TableColumn<Pedido, Integer> colId;
    @FXML
    private TableColumn<Pedido, String> colCliente;
    @FXML
    private TableColumn<Pedido, String> colEstado;

    private final ObservableList<Pedido> pedidos = FXCollections.observableArrayList();

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        colCliente.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(
                cellData.getValue().getIdCliente().getNome()
        ));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        List<Pedido> lista = pedidoRepository.findAll();
        pedidos.setAll(lista);
        tabelaPedidos.setItems(pedidos);
    }

    @FXML
    private void handleMarcarEntregue() {
        Pedido pedidoSelecionado = tabelaPedidos.getSelectionModel().getSelectedItem();
        if (pedidoSelecionado != null) {
            pedidoSelecionado.setEstado("Entregue");
            pedidoRepository.save(pedidoSelecionado);  // <- Salva na BD
            tabelaPedidos.refresh(); // Atualiza a tabela visualmente
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
}