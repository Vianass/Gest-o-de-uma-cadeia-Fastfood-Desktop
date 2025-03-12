package com.example.projecto2desktop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "armazem_produto")
public class ArmazemProduto {
    @Id
    @Column(name = "id_armazem_produto", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_armazem", nullable = false)
    private Armazem idArmazem;

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

    public Armazem getIdArmazem() {
        return idArmazem;
    }

    public void setIdArmazem(Armazem idArmazem) {
        this.idArmazem = idArmazem;
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