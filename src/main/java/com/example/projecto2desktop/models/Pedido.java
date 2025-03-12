package com.example.projecto2desktop.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @Column(name = "data_hora", nullable = false)
    private LocalDate dataHora;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}