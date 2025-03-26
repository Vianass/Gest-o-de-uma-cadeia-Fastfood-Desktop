package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Encomenda;
import com.example.projecto2desktop.repositories.EncomendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncomendaService {
    @Autowired
    private EncomendaRepository encomendaRepository;

    public List<Encomenda> listarEncomendas() {
        return encomendaRepository.findAll();
    }

    public Optional<Encomenda> buscarPorId(Integer id) {
        return encomendaRepository.findById(id);
    }

    public Encomenda salvarEncomenda(Encomenda encomenda) {
        return encomendaRepository.save(encomenda);
    }

    public void deletarEncomenda(Integer id) {
        encomendaRepository.deleteById(id);
    }
}
