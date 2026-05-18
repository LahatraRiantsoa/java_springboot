package org.example.backend.service;

import org.example.backend.entity.Categorie;
import java.util.List;

public interface CategorieService {
    List<Categorie> getAll();
    Categorie getById(Long id);
    Categorie save(Categorie categorie);
    void delete(Long id);
}
