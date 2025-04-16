package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.ArmazemIngrediente;
import com.example.projecto2desktop.repositories.ArmazemIngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmazemIngredienteService {

    private final ArmazemIngredienteRepository armazemIngredienteRepository;

    public ArmazemIngredienteService(ArmazemIngredienteRepository armazemIngredienteRepository) {
        this.armazemIngredienteRepository = armazemIngredienteRepository;
    }

    public List<ArmazemIngrediente> listarTodos() {
        return armazemIngredienteRepository.findAll();
    }

    public List<ArmazemIngrediente> listarPorArmazem(Integer idArmazem) {
        return armazemIngredienteRepository.findByArmazem_Id(idArmazem);
    }

    public ArmazemIngrediente guardar(ArmazemIngrediente armazemIngrediente) {
        return armazemIngredienteRepository.save(armazemIngrediente);
    }

    public void apagar(Integer id) {
        armazemIngredienteRepository.deleteById(id);
    }

    @Transactional
    public void retirarQuantidade(Integer armazemId, Integer ingredienteId, int quantidade) {
        ArmazemIngrediente ai = armazemIngredienteRepository.findByArmazemIdAndIngredienteId(armazemId, ingredienteId);

        if (ai == null) {
            throw new IllegalArgumentException("Este armazém não contém esse ingrediente!");
        }

        int stockAtual = ai.getQuantidade();
        if (quantidade > stockAtual) {
            // ❌ Se tentou retirar mais do que existe, lançamos um erro
            throw new IllegalArgumentException(
                    "Quantidade em stock insuficiente. Disponível: " + stockAtual
            );
        }

        // Se chegou aqui, há stock suficiente
        ai.setQuantidade(stockAtual - quantidade);
        armazemIngredienteRepository.save(ai);
    }
}
