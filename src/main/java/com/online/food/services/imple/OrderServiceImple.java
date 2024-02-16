package com.online.food.services.imple;

import com.online.food.modal.Customer;
import com.online.food.modal.Order;
import com.online.food.repository.OrderRepository;
import com.online.food.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImple implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order add(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order get(String id) {
        return this.orderRepository.findById(id).get();
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public void delete(Order order) {
        this.orderRepository.delete(order);
    }

    @Override
    public List<Order> findAllCustomerOrder(Customer customer) {
        return this.findAllCustomerOrder(customer);
    }

    @Override
    public Page<Order> findAllOrderParticularRestaurant(Long restaurnatId, Pageable pageable) {
        return this.orderRepository.findAllOrderParticularResturant(restaurnatId,pageable);
    }

    @Override
    public int countPendingOrderParticularRestaurant(Long restaurantId) {
        return this.orderRepository.countPendingOrderParticularRestaurant(restaurantId);
    }

    @Override
    public int countDeliveredOrderParticularRestaurant(Long restaurantId) {
        return this.orderRepository.countDeliveredOrderParticularRestaurant(restaurantId);
    }
}
