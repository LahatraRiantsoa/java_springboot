package org.example.backend;

import org.example.backend.entity.User;
import org.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Charge les donnees initiales au demarrage de l'application.
 * Cree un admin par defaut si aucun n'existe.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Cree un admin par defaut si il n'existe pas
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of("USER", "ADMIN"));
            admin.setEnabled(true);
            userRepository.save(admin);
            System.out.println("Admin cree : username=admin / password=admin123");
        }
    }
}
