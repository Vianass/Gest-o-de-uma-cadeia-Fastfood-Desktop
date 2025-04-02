package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.EncomendaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EncomendaProdutoRepository extends JpaRepository<EncomendaProduto, Integer> {

    List<EncomendaProduto> findByEncomenda_Id(Integer id);
}
