package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.CreateOrderRequest;
import org.example.backend.dto.OrderResponse;
import org.example.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST /api/orders — Creer une commande
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        OrderResponse response = orderService.createOrder(request, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    // GET /api/orders/my-orders — Voir mes commandes
    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponse>> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<OrderResponse> orders = orderService.getMyOrders(userDetails.getUsername());
        return ResponseEntity.ok(orders);
    }
}
