package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Ingrediente;
import com.example.projecto2desktop.repositories.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingrediente> listarIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente guardarIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public void apagarIngrediente(Integer id) {
        ingredienteRepository.deleteById(id);
    }

    public Ingrediente buscarPorId(Integer id) {
        return ingredienteRepository.findById(id).orElse(null);
    }
}
