package com.example.demo.controllers;


import com.example.demo.model.Product;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productss")
public class ProductController {
    @Autowired
    ProductService productService;

    @Value("E:/Codegym123/file/")
    private String fileUpload;


    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("product",new Product());
        return "product/create";
    }
    @PostMapping("/create")
    public String saveNewProduct(Model model, Product product){
        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImgSrc(fileName);
        productService.save(product);
        model.addAttribute("message", "New products was created succeeded!!!");

        return "redirect:/productss";
    }

    @GetMapping
    public ModelAndView listProducts(@RequestParam("searchName") Optional<String> name, @PageableDefault(value =3) Pageable pageable){
        String searchName = name.orElse("");
        Page<Product> products = productService.findAllByNameContaining(searchName,pageable);
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model){
        Product product = productService.findById(id).get();
        product.setImgSrc(product.getImgSrc());
        model.addAttribute("product",product);
        return "product/view";

    }



}
