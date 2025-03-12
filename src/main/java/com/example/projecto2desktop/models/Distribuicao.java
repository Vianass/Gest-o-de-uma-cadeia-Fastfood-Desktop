package com.example.projecto2desktop.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "distribuicao")
public class Distribuicao {
    @Id
    @Column(name = "id_distribuicao", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_armazem", nullable = false)
    private Armazem idArmazem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade idUnidade;

    @Column(name = "data_envio", nullable = false)
    private LocalDate dataEnvio;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

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

    public Unidade getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Unidade idUnidade) {
        this.idUnidade = idUnidade;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}