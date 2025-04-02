package com.example.projecto2desktop.models;

import javafx.beans.property.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Column(name = "id_fornecedor", nullable = false)
    private Integer idFornecedor;

    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

    // Getters e setters normais

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    // JavaFX properties (não persistem na BD, só para TableView/binding)
    public StringProperty nomeProperty() {
        return new SimpleStringProperty(nome);
    }

    public ObjectProperty<BigDecimal> precoProperty() {
        return new SimpleObjectProperty<>(preco);
    }

    public StringProperty descricaoProperty() {
        return new SimpleStringProperty(descricao);
    }

    public StringProperty categoriaProperty() {
        return new SimpleStringProperty(categoria);
    }

    public IntegerProperty idFornecedorProperty() {
        return new SimpleIntegerProperty(idFornecedor);
    }

    public IntegerProperty idPedidoProperty() {
        return new SimpleIntegerProperty(idPedido);
    }

    @Override
    public String toString() {
        return nome + " (€" + preco + ")";
    }
}
