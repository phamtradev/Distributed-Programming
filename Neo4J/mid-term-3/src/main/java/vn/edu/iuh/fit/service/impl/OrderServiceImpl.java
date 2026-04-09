package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.repository.OrderRepository;
import vn.edu.iuh.fit.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public double calculateTotalOrder(String orderID) {
        if (orderID == null || orderID.trim().isEmpty()) {
            throw new IllegalArgumentException("Order id ko dc null");
        }
        return orderRepository.calculateTotalOrder(orderID);
    }
}
