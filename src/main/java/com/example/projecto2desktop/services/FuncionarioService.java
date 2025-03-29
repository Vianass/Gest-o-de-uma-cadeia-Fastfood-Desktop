package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Funcionario;
import com.example.projecto2desktop.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Apenas para autenticação (não é necessário método de registo se não quiseres)
    public boolean autenticar(String email, String password) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);
        return funcionario.isPresent() && encoder.matches(password, funcionario.get().getPassword());
    }

   /* public boolean autenticar(String email, String password) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);

        if (funcionario.isPresent()) {
            Funcionario f = funcionario.get();
            System.out.println("DEBUG -> Email encontrado: " + f.getEmail());
            System.out.println("DEBUG -> Hash na base de dados: " + f.getPassword());
            System.out.println("DEBUG -> Password correta? " + encoder.matches(password, f.getPassword()));
            return encoder.matches(password, f.getPassword());
        } else {
            System.out.println("DEBUG -> Email não encontrado!");
            return false;
        }
    }*/

    // Apenas se um dia quiseres guardar/encriptar passwords
    public Funcionario guardarFuncionario(Funcionario funcionario) {
        funcionario.setPassword(encoder.encode(funcionario.getPassword()));
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorEmail(String email) {
        return funcionarioRepository.findByEmail(email);
    }
}