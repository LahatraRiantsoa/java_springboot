package org.example.backend.repository;

import org.example.backend.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    // Toutes les méthodes (save, findall, delete) sont créées automatiquement !
}
