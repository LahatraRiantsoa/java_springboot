package org.example.backend.service;

import org.example.backend.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getAll(int page);
    Product getById(Long id);
    List<Product> search(String name, String category);
    Product save(Product product);
    void delete(Long id);
}
