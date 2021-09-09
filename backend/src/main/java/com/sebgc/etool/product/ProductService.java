package com.sebgc.etool.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SurveyService")
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
