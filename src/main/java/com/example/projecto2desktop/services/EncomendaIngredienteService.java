package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.EncomendaIngrediente;
import com.example.projecto2desktop.repositories.EncomendaIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaIngredienteService {

    private final EncomendaIngredienteRepository repository;

    public EncomendaIngredienteService(EncomendaIngredienteRepository repository) {
        this.repository = repository;
    }

    public EncomendaIngrediente salvar(EncomendaIngrediente encomendaIngrediente) {
        return repository.save(encomendaIngrediente);
    }

    public List<EncomendaIngrediente> listar() {
        return repository.findAll();
    }
}
