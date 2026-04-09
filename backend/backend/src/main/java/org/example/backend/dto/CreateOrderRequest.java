package org.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateOrderRequest {

    @NotNull(message = "L'identifiant du produit est obligatoire")
    private Long productId;

    @NotBlank
    private String productName;

    @NotNull(message = "La quantite est obligatoire")
    @Positive(message = "La quantite doit etre positive")
    private Integer quantite;

    @NotNull(message = "Le prix unitaire est obligatoire")
    @Positive(message = "Le prix doit etre positif")
    private Double prixUnitaire;
}
