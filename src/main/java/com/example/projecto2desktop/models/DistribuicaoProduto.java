package com.example.projecto2desktop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "distribuicao_produto")
public class DistribuicaoProduto {
    @Id
    @Column(name = "id_distribuicao_produto", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_distribuicao", nullable = false)
    private Distribuicao idDistribuicao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Distribuicao getIdDistribuicao() {
        return idDistribuicao;
    }

    public void setIdDistribuicao(Distribuicao idDistribuicao) {
        this.idDistribuicao = idDistribuicao;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}