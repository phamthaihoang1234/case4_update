package com.example.demo.controllers;


import com.example.demo.model.Cart;

import com.example.demo.model.Order_Session;
import com.example.demo.model.Product;
import com.example.demo.service.OrderSession.OrderService;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/carts")
@Controller
@SessionAttributes("carts")
public class CartController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @ModelAttribute("carts")
    public Cart getCart(){return new Cart();
    }

    @GetMapping
    public String showCart(@ModelAttribute("carts") Cart cart){
        return "shoppingCart/cart";
    }

    @GetMapping("/{id}")
    public String orderProduct(@PathVariable("id") Long id , @ModelAttribute("carts") Cart cart){
        Product product = productService.findById(id).get();
        Order_Session itemLine = cart.getOrderSession(product);
        itemLine.setQuantity(itemLine.getQuantity()+1);
        return "redirect:/carts";

    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, @ModelAttribute("carts") Cart cart){

        Product product = productService.findById(id).get();
        if(cart.getOrderSession(product).getQuantity() > 1){
            cart.getOrderSession(product).setQuantity(cart.getOrderSession(product).getQuantity()-1);
        }else{
            cart.removeOrderSessionByProduct(product);
        }

        return "redirect:/carts";
    }


    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/order")
    public String orderConfirm(@ModelAttribute("carts") Cart cart){

        List<Order_Session> orderSessions = cart.getOrderSessions();

        for(Order_Session order_session: orderSessions){
            order_session.setUsername(getPrincipal());
            order_session.setNameOfProduct(order_session.getProduct().getName());
            order_session.setQuantity(order_session.getQuantity());
            order_session.setPrice(order_session.getProduct().getPrice());
            order_session.setSubtotal(order_session.getPrice()*order_session.getQuantity());
            orderService.save(order_session);


        }

        return "redirect:/carts";

    }

}
