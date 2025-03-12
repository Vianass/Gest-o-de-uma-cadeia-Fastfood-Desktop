package com.example.projecto2desktop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "encomenda_produto")
public class EncomendaProduto {
    @Id
    @Column(name = "id_encomenda_produto", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_encomenda", nullable = false)
    private Encomenda idEncomenda;

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

    public Encomenda getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(Encomenda idEncomenda) {
        this.idEncomenda = idEncomenda;
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