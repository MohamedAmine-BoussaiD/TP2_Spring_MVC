package com.sdia.tp2.web;


import com.sdia.tp2.entities.Product;
import com.sdia.tp2.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("productsList", products);
        return "products";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id){
        productRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/")
    public String home(Model model){
        return "redirect:/index";
    }

    @GetMapping("/newProduct")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "new-product";}

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product product , BindingResult bindingResult , Model model) {
        if(bindingResult.hasErrors()) return "new-product";
        productRepository.save(product);
        return "redirect:/newProduct";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Product product = productRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/updateProduct")
    public String updateProduct( @Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-product";
        }
        productRepository.save(product);
        return "redirect:/index";
    }
}
