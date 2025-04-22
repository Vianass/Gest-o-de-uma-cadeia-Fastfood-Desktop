package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {

    @Query("SELECT e FROM Encomenda e JOIN FETCH e.fornecedor f JOIN FETCH e.armazem a")
    List<Encomenda> findAllComFornecedorEArmazem();

}