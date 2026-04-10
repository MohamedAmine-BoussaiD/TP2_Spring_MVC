package com.sdia.tp2;

import com.sdia.tp2.entities.Product;
import com.sdia.tp2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
//import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
//import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication( exclude = {SecurityAutoConfiguration.class , UserDetailsServiceAutoConfiguration.class} ) // desactiver spring security
@SpringBootApplication()
public class Tp2Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }


    @Bean
    public CommandLineRunner start (ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(5400)
                    .quantity(12)
                    .build());

            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(1200)
                    .quantity(11)
                    .build());

            productRepository.save(Product.builder()
                    .name("Smart Phone")
                    .price(4500)
                    .quantity(33)
                    .build());

            productRepository.findAll().forEach( p -> System.out.println(p.toString()));
        };
    }
}
