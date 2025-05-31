package com.example.bdccensetspringenset;

import com.example.bdccensetspringenset.entities.Product;
import com.example.bdccensetspringenset.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BdccEnsetSpringEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdccEnsetSpringEnsetApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("PC1").price(3000).quantity(100).build());
            productRepository.save(Product.builder().name("TV1").price(4000).quantity(15).build());
            productRepository.save(Product.builder().name("Phone").price(2500).quantity(12).build());
            productRepository.findAll().forEach(p-> { System.out.println(p.toString());});

        };
    }

}
