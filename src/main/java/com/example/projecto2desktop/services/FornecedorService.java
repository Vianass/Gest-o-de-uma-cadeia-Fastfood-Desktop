package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Fornecedor;
import com.example.projecto2desktop.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deletarFornecedor(Integer id) {
        fornecedorRepository.deleteById(id);
    }
}