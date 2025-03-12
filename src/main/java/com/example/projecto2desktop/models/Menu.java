package com.example.projecto2desktop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "id_menu", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade idUnidade;

    @Column(name = "nome", nullable = false, length = Integer.MAX_VALUE)
    private String nome;

    @Column(name = "descricao", nullable = false, length = Integer.MAX_VALUE)
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Unidade getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Unidade idUnidade) {
        this.idUnidade = idUnidade;
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

}