package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Distribuicao;
import com.example.projecto2desktop.repositories.DistribuicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistribuicaoService {
    @Autowired
    private DistribuicaoRepository distribuicaoRepository;

    public List<Distribuicao> listarDistribuicoes() {
        return distribuicaoRepository.findAll();
    }

    public Optional<Distribuicao> buscarPorId(Integer id) {
        return distribuicaoRepository.findById(id);
    }

    public Distribuicao salvarDistribuicao(Distribuicao distribuicao) {
        return distribuicaoRepository.save(distribuicao);
    }

    public void deletarDistribuicao(Integer id) {
        distribuicaoRepository.deleteById(id);
    }
}