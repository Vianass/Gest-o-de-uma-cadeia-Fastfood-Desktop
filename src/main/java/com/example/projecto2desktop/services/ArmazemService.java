package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Armazem;
import com.example.projecto2desktop.repositories.ArmazemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmazemService {
    @Autowired
    private ArmazemRepository armazemRepository;

    public List<Armazem> listarArmazens() {
        return armazemRepository.findAll();
    }

    public Optional<Armazem> buscarPorId(Integer id) {
        return armazemRepository.findById(id);
    }

    public Armazem salvarArmazem(Armazem armazem) {
        return armazemRepository.save(armazem);
    }

    public void deletarArmazem(Integer id) {
        armazemRepository.deleteById(id);
    }
}

