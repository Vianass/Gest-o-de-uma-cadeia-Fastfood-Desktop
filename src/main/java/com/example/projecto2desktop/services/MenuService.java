package com.example.projecto2desktop.services;

import com.example.projecto2desktop.models.Menu;
import com.example.projecto2desktop.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listarMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> buscarPorId(Integer id) {
        return menuRepository.findById(id);
    }

    public Menu salvarMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deletarMenu(Integer id) {
        menuRepository.deleteById(id);
    }
}