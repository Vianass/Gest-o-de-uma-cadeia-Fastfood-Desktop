package com.example.projecto2desktop.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_produto")
public class MenuProduto {
    @Id
    @Column(name = "id_menu_produto", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu idMenu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;

    @Column(name = "preco_personalizado", nullable = false, precision = 38, scale = 2)
    private BigDecimal precoPersonalizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public BigDecimal getPrecoPersonalizado() {
        return precoPersonalizado;
    }

    public void setPrecoPersonalizado(BigDecimal precoPersonalizado) {
        this.precoPersonalizado = precoPersonalizado;
    }

}