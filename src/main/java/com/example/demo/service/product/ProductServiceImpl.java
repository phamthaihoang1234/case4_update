package com.example.demo.service.product;

import com.example.demo.model.Order_Session;
import com.example.demo.model.Product;

import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Order_Session> findByid(Long id) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product remove(Long id) {
        Product product = productRepository.findById(id).get();
        if(product != null){
            productRepository.delete(product);
        }
        return product;
    }
}

