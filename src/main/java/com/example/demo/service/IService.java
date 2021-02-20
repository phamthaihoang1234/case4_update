package com.example.demo.service;

import com.example.demo.model.Order_Session;
import com.example.demo.model.Product;

import java.util.Optional;

public interface IService<E> {
    Iterable<E> findAll();
    Optional<Product>findById(Long id);
    Optional<Order_Session>findByid(Long id);
    E save(E e);
    E remove(Long id);
}
