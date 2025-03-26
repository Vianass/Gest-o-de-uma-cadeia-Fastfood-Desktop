package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Pagamento;
import com.example.projecto2desktop.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPorId(Integer id) {
        return pagamentoRepository.findById(id);
    }

    public Pagamento salvarPagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public void deletarPagamento(Integer id) {
        pagamentoRepository.deleteById(id);
    }
}
