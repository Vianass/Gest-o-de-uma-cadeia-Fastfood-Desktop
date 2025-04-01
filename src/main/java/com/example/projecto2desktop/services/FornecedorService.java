package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Fornecedor;
import com.example.projecto2desktop.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // --- CRUD BÁSICO ---

    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    @Transactional
    public Fornecedor adicionarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public void removerFornecedor(Integer id) {
        fornecedorRepository.deleteById(id);
    }

    public boolean existeFornecedor(Integer id) {
        return fornecedorRepository.existsById(id);
    }

    // --- Avaliação persistente (novo) ---

    @Transactional
    public void salvarAvaliacao(Integer idFornecedor, Double avaliacao) {
        Fornecedor f = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));
        f.setAvaliacao(avaliacao);
        fornecedorRepository.save(f);
    }

    public Double buscarAvaliacao(Integer idFornecedor) {
        return fornecedorRepository.findById(idFornecedor)
                .map(Fornecedor::getAvaliacao)
                .orElse(0.0);
    }
}
