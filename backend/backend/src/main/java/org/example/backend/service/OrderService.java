package org.example.backend.service;

import org.example.backend.dto.CreateOrderRequest;
import org.example.backend.dto.OrderResponse;
import org.example.backend.entity.Order;
import org.example.backend.entity.User;
import org.example.backend.repository.OrderRepository;
import org.example.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    // Cree une nouvelle commande pour l'utilisateur connecte
    public OrderResponse createOrder(CreateOrderRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouve"));

        Order order = new Order();
        order.setUser(user);
        order.setProductId(request.getProductId());
        order.setProductName(request.getProductName());
        order.setQuantite(request.getQuantite());
        order.setPrixUnitaire(request.getPrixUnitaire());
        order.setTotalAmount(request.getQuantite() * request.getPrixUnitaire());

        Order saved = orderRepository.save(order);
        return toResponse(saved);
    }

    // Retourne les commandes de l'utilisateur connecte
    public List<OrderResponse> getMyOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouve"));

        return orderRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Retourne toutes les commandes (pour l'admin)
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Convertit une entite Order en DTO OrderResponse
    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus().name(),
                order.getProductName(),
                order.getQuantite()
        );
    }
}
