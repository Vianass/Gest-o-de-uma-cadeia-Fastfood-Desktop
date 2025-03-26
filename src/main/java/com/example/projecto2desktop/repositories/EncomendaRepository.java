package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Integer> {
}