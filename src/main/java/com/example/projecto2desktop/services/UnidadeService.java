package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Unidade;
import com.example.projecto2desktop.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {
    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> listarUnidades() {
        return unidadeRepository.findAll();
    }

    public Optional<Unidade> buscarPorId(Integer id) {
        return unidadeRepository.findById(id);
    }

    public Unidade salvarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public void deletarUnidade(Integer id) {
        unidadeRepository.deleteById(id);
    }
}