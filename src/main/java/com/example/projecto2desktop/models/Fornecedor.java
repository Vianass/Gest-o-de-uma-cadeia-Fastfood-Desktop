package com.example.projecto2desktop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "contato", nullable = false)
    private String contato;

    @Column(name = "avaliacao")
    private Double avaliacao;


    // Construtor padrão (obrigatório para JPA)
    public Fornecedor() {}
}