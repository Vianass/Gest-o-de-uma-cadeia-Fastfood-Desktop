package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Fornecedor;
import com.example.projecto2desktop.models.FornecedoresIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorIngredienteRepository extends JpaRepository<FornecedoresIngrediente, Integer> {
    List<FornecedoresIngrediente> findByFornecedorId(Integer idFornecedor);
}
