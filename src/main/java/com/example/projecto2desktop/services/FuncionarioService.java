package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Funcionario;
import com.example.projecto2desktop.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Tenta autenticar o funcionário pelo email e password.
     * Se a password estiver correta, retorna Optional<Funcionario>.
     * Caso contrário, retorna Optional.empty().
     */
    public Optional<Funcionario> autenticar(String email, String password) {
        Optional<Funcionario> opt = funcionarioRepository.findByEmail(email);
        if (opt.isPresent()) {
            Funcionario f = opt.get();
            if (encoder.matches(password, f.getPassword())) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    /**
     * Regista ou atualiza o funcionário, encriptando a password.
     */
    public Funcionario guardarFuncionario(Funcionario funcionario) {
        funcionario.setPassword(encoder.encode(funcionario.getPassword()));
        return funcionarioRepository.save(funcionario);
    }

    /**
     * Busca um funcionário pelo email.
     */
    public Optional<Funcionario> buscarPorEmail(String email) {
        return funcionarioRepository.findByEmail(email);
    }
}