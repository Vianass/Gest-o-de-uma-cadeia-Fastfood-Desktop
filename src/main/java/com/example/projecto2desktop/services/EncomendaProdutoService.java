package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.EncomendaProduto;
import com.example.projecto2desktop.repositories.EncomendaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaProdutoService {

    @Autowired
    private EncomendaProdutoRepository encomendaProdutoRepository;

    public List<EncomendaProduto> listarTodos() {
        return encomendaProdutoRepository.findAll();
    }

    public List<EncomendaProduto> listarPorEncomenda(Integer idEncomenda) {
        return encomendaProdutoRepository.findByEncomenda_Id(idEncomenda);
    }

    public EncomendaProduto salvar(EncomendaProduto encomendaProduto) {
        return encomendaProdutoRepository.save(encomendaProduto);
    }

    public void remover(Integer id) {
        encomendaProdutoRepository.deleteById(id);
    }
}
