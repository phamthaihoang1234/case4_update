package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends IService<Product> {
    Page<Product> findAllByNameContaining(String name , Pageable pageable);
}
