package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    Page<Product> findAllByNameContaining(String name , Pageable pageable);
}
