package org.example.backend.service.impl;

import org.example.backend.entity.Categorie;
import org.example.backend.repository.CategorieRepository;
import org.example.backend.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public void delete(Long id) {
        categorieRepository.deleteById(id);
    }
}
