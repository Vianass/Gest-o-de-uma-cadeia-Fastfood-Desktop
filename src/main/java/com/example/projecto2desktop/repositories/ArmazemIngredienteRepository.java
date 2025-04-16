package com.example.projecto2desktop.repositories;

import com.example.projecto2desktop.models.ArmazemIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArmazemIngredienteRepository extends JpaRepository<ArmazemIngrediente, Integer> {
    List<ArmazemIngrediente> findByArmazem_Id(Integer idArmazem);
    ArmazemIngrediente findByArmazemIdAndIngredienteId(Integer armazemId, Integer ingredienteId);

}
