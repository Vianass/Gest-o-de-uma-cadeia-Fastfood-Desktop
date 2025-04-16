package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
