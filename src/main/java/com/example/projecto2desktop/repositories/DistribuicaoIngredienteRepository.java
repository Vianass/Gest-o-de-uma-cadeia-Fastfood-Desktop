package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.DistribuicaoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribuicaoIngredienteRepository extends JpaRepository<DistribuicaoIngrediente, Integer> {
}
