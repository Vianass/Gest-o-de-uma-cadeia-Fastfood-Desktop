package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmail(String email);
}