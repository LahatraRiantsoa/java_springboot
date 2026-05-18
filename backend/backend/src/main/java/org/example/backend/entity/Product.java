package org.example.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du produit ne peut pas être vide.")
    @Size(min = 3, max = 100, message = "Le nom du produit doit contenir entre 3 et 100 caractères.")
    private String name;

    @NotBlank(message = "La description ne peut pas être vide.")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Le prix ne peut pas être nul.")
    @Positive(message = "Le prix doit être un nombre positif.")
    private Double price;

    @NotNull(message = "La quantité en stock ne peut pas être nulle.")
    @Positive(message = "La quantité en stock doit être un nombre positif.")
    private Integer stockQuantity;

    private String lienImage;

    private LocalDateTime createdAt;

    @NotNull(message = "La catégorie ne peut pas être nulle.")
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie category;
}
