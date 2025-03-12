package com.example.projecto2desktop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @Column(name = "id_produto", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false, length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "preco", nullable = false, precision = 38, scale = 2)
    private BigDecimal preco;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @ColumnDefault("nextval('produto_id_fornecedor_seq')")
    @Column(name = "id_fornecedor", nullable = false)
    private Integer idFornecedor;

    @ColumnDefault("nextval('produto_id_pedido_seq')")
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

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

}