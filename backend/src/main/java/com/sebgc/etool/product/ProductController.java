package com.sebgc.etool.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("ProductController")
@RequestMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public  ResponseEntity<List<Product>> findProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProducts());
    }
}
