package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Cliente;
import com.example.projecto2desktop.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Registar um novo cliente (com password encriptada)
    public Cliente registarCliente(Cliente cliente) {
        cliente.setPassword(encoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    // Autenticar login por email e password
    public boolean autenticar(String email, String password) {
        Optional<Cliente> optCliente = clienteRepository.findByEmail(email);
        if (optCliente.isEmpty()) return false;

        Cliente cliente = optCliente.get();
        return encoder.matches(password, cliente.getPassword());
    }

    // Buscar cliente por email
    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID
    public Optional<Cliente> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    // Salvar ou atualizar cliente (sem encriptar password!)
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Remover cliente
    public void deletarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}
