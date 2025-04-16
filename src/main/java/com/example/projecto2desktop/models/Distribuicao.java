package com.example.projecto2desktop.models;

import com.example.projecto2desktop.models.Armazem;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "distribuicao")
public class Distribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- Esta linha é ESSENCIAL
    @Column(name = "id_distribuicao")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_armazem", nullable = false)
    private Armazem idArmazem;

    @Column(name = "data_envio", nullable = false)
    private LocalDate dataEnvio;

    // (se ainda tiveres unidade aqui, e não em DistribuicaoIngrediente)
    @ManyToOne
    @JoinColumn(name = "id_unidade", nullable = false)
    private Unidade idUnidade;

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
}