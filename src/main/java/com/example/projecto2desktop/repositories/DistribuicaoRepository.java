package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Distribuicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribuicaoRepository extends JpaRepository<Distribuicao, Integer> {
}