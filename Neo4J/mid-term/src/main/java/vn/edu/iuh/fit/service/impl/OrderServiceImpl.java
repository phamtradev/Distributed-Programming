package vn.edu.iuh.fit.service.impl;

import lombok.RequiredArgsConstructor;
import vn.edu.iuh.fit.repository.OrderRepository;
import vn.edu.iuh.fit.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public double calculateTotalOrder(String orderID) {
        if (orderID == null || orderID.trim().isEmpty()) {
            throw new IllegalArgumentException("orderID không được null hoặc rỗng");
        }
        return orderRepository.calculateTotalOrder(orderID.trim());
    }
}
