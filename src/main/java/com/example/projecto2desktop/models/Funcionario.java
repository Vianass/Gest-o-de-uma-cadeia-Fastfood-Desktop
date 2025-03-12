package com.example.projecto2desktop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @Column(name = "id_funcionario", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade idUnidade;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cargo", nullable = false, length = 100)
    private String cargo;

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}