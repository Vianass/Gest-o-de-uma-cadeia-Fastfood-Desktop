package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.DistribuicaoIngrediente;
import com.example.projecto2desktop.repositories.DistribuicaoIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistribuicaoIngredienteService {

    private final DistribuicaoIngredienteRepository repository;

    public DistribuicaoIngredienteService(DistribuicaoIngredienteRepository repository) {
        this.repository = repository;
    }

    public DistribuicaoIngrediente salvar(DistribuicaoIngrediente di) {
        return repository.save(di);
    }

    public List<DistribuicaoIngrediente> listarTodos() {
        return repository.findAll();
    }

    public void apagar(Integer id) {
        repository.deleteById(id);
    }
}
