package org.example.backend.controller;

import org.example.backend.dto.OrderResponse;
import org.example.backend.dto.UpdateUserRequest;
import org.example.backend.dto.UserDTO;
import org.example.backend.entity.User;
import org.example.backend.repository.UserRepository;
import org.example.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final OrderService orderService;

    public AdminController(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    // GET /api/admin/users — Liste tous les utilisateurs
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // PUT /api/admin/users/{id} — Modifier un utilisateur (roles, enabled)
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestBody UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve"));

        if (request.getRoles() != null) {
            user.setRoles(request.getRoles());
        }
        user.setEnabled(request.isEnabled());

        userRepository.save(user);
        return ResponseEntity.ok(toUserDTO(user));
    }

    // GET /api/admin/orders — Liste toutes les commandes
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Convertit User en UserDTO (sans exposer le mot de passe)
    private UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles(),
                user.isEnabled(),
                user.getCreatedAt()
        );
    }
}
