package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Fornecedor;
import com.example.projecto2desktop.models.FornecedoresIngrediente;
import com.example.projecto2desktop.repositories.FornecedorIngredienteRepository;
import com.example.projecto2desktop.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorIngredienteService {

    @Autowired
    private FornecedorIngredienteRepository repository;

    public List<FornecedoresIngrediente> listarTodos() {
        return repository.findAll();
    }

    public List<FornecedoresIngrediente> listarPorFornecedor(Integer idFornecedor) {
        return repository.findByFornecedorId(idFornecedor);
    }

    public FornecedoresIngrediente guardar(FornecedoresIngrediente fi) {
        return repository.save(fi);
    }

    public void apagar(Integer id) {
        repository.deleteById(id);
    }
}
