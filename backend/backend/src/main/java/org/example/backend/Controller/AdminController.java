package org.example.backend.Controller;

import jakarta.validation.Valid;
import org.example.backend.entity.Categorie;
import org.example.backend.entity.Product;
import org.example.backend.service.CategorieService;
import org.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategorieService categorieService;

    // --- CRUD pour les Produits ---

    @GetMapping("/products")
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        return productService.getAll(page);
    }

    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        product.setId(id);
        return productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    // --- CRUD pour les Catégories ---

    @GetMapping("/categories")
    public List<Categorie> getAllCategories() {
        return categorieService.getAll();
    }

    @PostMapping("/categories")
    public Categorie createCategorie(@Valid @RequestBody Categorie categorie) {
        return categorieService.save(categorie);
    }

    @PutMapping("/categories/{id}")
    public Categorie updateCategorie(@PathVariable Long id, @Valid @RequestBody Categorie categorie) {
        categorie.setId(id);
        return categorieService.save(categorie);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategorie(@PathVariable Long id) {
        categorieService.delete(id);
    }
}
