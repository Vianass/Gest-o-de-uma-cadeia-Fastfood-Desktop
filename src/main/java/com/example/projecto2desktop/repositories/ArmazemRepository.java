package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Armazem;
import com.example.projecto2desktop.models.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmazemRepository extends JpaRepository<Armazem, Integer> {
    @Repository
    interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
    }
}
