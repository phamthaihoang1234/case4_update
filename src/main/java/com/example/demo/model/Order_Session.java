package com.example.demo.model;

import javax.persistence.*;


@Entity
@Table(name = "order_session")
public class Order_Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nameOfProduct;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Long price;



    @OneToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false)
    private Product product;

    @Column(nullable = false)
    private Long Subtotal;

    public Order_Session() {
    }



    public Order_Session(Product product) {
        this.product = product;
    }

    public Order_Session(Long id, String username, String nameOfProduct, int quantity, Long price, Product product, Long subtotal) {
        this.id = id;
        this.username = username;
        this.nameOfProduct = nameOfProduct;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        Subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Long subtotal) {
        Subtotal = subtotal;
    }
}
