package com.example.demo.service.OrderSession;

import com.example.demo.model.Order_Session;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;


    @Override
    public Iterable<Order_Session> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Order_Session> findByid(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order_Session save(Order_Session order_session) {
        return orderRepository.save(order_session);
    }

    @Override
    public Order_Session remove(Long id) {
        Order_Session orderSession = orderRepository.findById(id).get();
        if(orderSession != null){
            orderRepository.delete(orderSession);
        }
        return orderSession;
    }
}
