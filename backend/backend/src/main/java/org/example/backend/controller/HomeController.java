package org.example.backend.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // GET / — Retourne une image statique (ressource publique)
    @GetMapping("/")
    public ResponseEntity<Resource> home() {
        Resource image = new ClassPathResource("static/images/home.png");
        if (image.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        }
        return ResponseEntity.ok().build();
    }
}
