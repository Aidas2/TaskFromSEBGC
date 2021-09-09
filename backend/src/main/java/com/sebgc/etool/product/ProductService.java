package com.sebgc.etool.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SurveyService")
public class ProductService {

    @Autowired
    ProductRepository productRepository;
}
